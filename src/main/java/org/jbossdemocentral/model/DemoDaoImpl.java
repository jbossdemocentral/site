package org.jbossdemocentral.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;
import org.jbossdemocentral.model.git.ConfigFileResponse;
import org.jbossdemocentral.model.git.Repository;

public class DemoDaoImpl implements DemoDao {

	private static final String GITHUB_API_SECRET_ENV_VAR = "GITHUB_API_SECRET";
	public static final String GITHUB_API_CLIENT_ID_ENV_VAR = "GITHUB_API_CLIENT_ID";

	private String githubApiClientId;
	private String githubApiSecret;

	Logger log = Logger.getLogger(this.getClass().getName());

	public DemoDaoImpl() {
		super();
		Map<String, String> env = System.getenv();
		this.githubApiClientId = System.getenv(GITHUB_API_CLIENT_ID_ENV_VAR);
		this.githubApiSecret = System.getenv(GITHUB_API_SECRET_ENV_VAR);

		if (this.githubApiClientId == null || "".equals(this.githubApiClientId)) {
			throw new RuntimeException("Missing environment variable "
					+ GITHUB_API_CLIENT_ID_ENV_VAR);
		}
		if (this.githubApiClientId == null || "".equals(this.githubApiClientId)) {
			throw new RuntimeException("Missing environment variable "
					+ GITHUB_API_SECRET_ENV_VAR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbossdemocentral.model.DemoDao#getDemos()
	 */
	@Override
	public List<Demo> getDemos() {
		List<Demo> list = new ArrayList<Demo>();

		ClientRequest request = new ClientRequest(
				"https://api.github.com/orgs/jbossdemocentral/repos");
		request.queryParameter("per_page", "200");
		log.info("Using Git Hub ID " + githubApiClientId.substring(0, 5)
				+ "**************");
		request.queryParameter("client_id", githubApiClientId);
		log.info("Using Git Hub Secret **" + githubApiSecret.substring(2, 7)
				+ "*********************************");
		request.queryParameter("client_secret", githubApiSecret);

		ClientResponse<List<Repository>> repolist;
		try {
			repolist = request.get(new GenericType<List<Repository>>() {
			});
			List<Repository> repos = repolist.getEntity();
			for (Repository repo : repos) {
				log.info("Found repository with name " + repo.getName());
				Demo demo = readRepoConfig(repo.getUrl()
						+ "/contents/.demo-config.json");

				// For backward compatibility
				if (demo == null) {
					demo = readRepoConfig(repo.getUrl()
							+ "/contents/demo-config.json");
				}

				if (demo != null && demo.isPublished()) {
					demo.setId(repo.getId());
					list.add(demo);
				}

			}
		} catch (Exception e) {
			log.log(Level.WARNING,
					"Failed to parse response from the GitHub API");
			e.printStackTrace();
		}
		return list;
	}

	private Demo readRepoConfig(String url) throws Exception, IOException,
			JsonParseException, JsonMappingException {
		ClientRequest clientRequest = new ClientRequest(url);

		clientRequest.queryParameter("client_id", githubApiClientId);
		clientRequest.queryParameter("client_secret", githubApiSecret);
		ConfigFileResponse configFileResponse = clientRequest.get(
				ConfigFileResponse.class).getEntity();
		if (configFileResponse != null
				&& configFileResponse.getContent() != null) {
			log.info("Found config file ");
			ObjectMapper mapper = new ObjectMapper();
			byte[] configFileContent = Base64.decodeBase64(configFileResponse
					.getContent().getBytes());
			//log.info(new String(configFileContent));
			Demo demo = mapper.readValue(configFileContent, Demo.class);
			log.info("Found demo with name "
					+ demo.getName()
					+ (demo.isPublished() ? ", that is pulblished"
							: ", that is NOT publised"));
			return demo;
		}
		return null;
	}

}
