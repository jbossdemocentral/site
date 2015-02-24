package org.jbossdemocentral.model.git;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
	String id;
	Owner owner;
	String name;
	String full_name;
	String description;
	@JsonProperty("private") boolean private_access;
    boolean fork;
    String url;
    String html_url;
    String clone_url;
    String git_url;
    String ssh_url;
    String svn_url;
    String mirror_url;
    String homepage;
    String language;
    int forks_count;
    int stargazers_count;
    int watchers_count;
    int size;
    String default_branch;
    int open_issues_count;
    boolean has_issues;
    boolean has_wiki;
    boolean has_pages;
    boolean has_downloads;
    String pushed_at;
    String created_at;
    String updated_at;
    Map<String,Boolean> permissions;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public boolean isFork() {
		return fork;
	}
	public void setFork(boolean fork) {
		this.fork = fork;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getClone_url() {
		return clone_url;
	}
	public void setClone_url(String clone_url) {
		this.clone_url = clone_url;
	}
	public String getGit_url() {
		return git_url;
	}
	public void setGit_url(String git_url) {
		this.git_url = git_url;
	}
	public String getSsh_url() {
		return ssh_url;
	}
	public void setSsh_url(String ssh_url) {
		this.ssh_url = ssh_url;
	}
	public String getSvn_url() {
		return svn_url;
	}
	public void setSvn_url(String svn_url) {
		this.svn_url = svn_url;
	}
	public String getMirror_url() {
		return mirror_url;
	}
	public void setMirror_url(String mirror_url) {
		this.mirror_url = mirror_url;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getForks_count() {
		return forks_count;
	}
	public void setForks_count(int forks_count) {
		this.forks_count = forks_count;
	}
	public int getStargazers_count() {
		return stargazers_count;
	}
	public void setStargazers_count(int stargazers_count) {
		this.stargazers_count = stargazers_count;
	}
	public int getWatchers_count() {
		return watchers_count;
	}
	public void setWatchers_count(int watchers_count) {
		this.watchers_count = watchers_count;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getDefault_branch() {
		return default_branch;
	}
	public void setDefault_branch(String default_branch) {
		this.default_branch = default_branch;
	}
	public int getOpen_issues_count() {
		return open_issues_count;
	}
	public void setOpen_issues_count(int open_issues_count) {
		this.open_issues_count = open_issues_count;
	}
	public boolean isHas_issues() {
		return has_issues;
	}
	public void setHas_issues(boolean has_issues) {
		this.has_issues = has_issues;
	}
	public boolean isHas_wiki() {
		return has_wiki;
	}
	public void setHas_wiki(boolean has_wiki) {
		this.has_wiki = has_wiki;
	}
	public boolean isHas_pages() {
		return has_pages;
	}
	public void setHas_pages(boolean has_pages) {
		this.has_pages = has_pages;
	}
	public boolean isHas_downloads() {
		return has_downloads;
	}
	public void setHas_downloads(boolean has_downloads) {
		this.has_downloads = has_downloads;
	}
	public String getPushed_at() {
		return pushed_at;
	}
	public void setPushed_at(String pushed_at) {
		this.pushed_at = pushed_at;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public Map<String, Boolean> getPermissions() {
		return permissions;
	}
	public void setPermissions(Map<String, Boolean> permissions) {
		this.permissions = permissions;
	}
	public boolean isPrivate_access() {
		return private_access;
	}
	public void setPrivate_access(boolean private_access) {
		this.private_access = private_access;
	}
	
	
}
