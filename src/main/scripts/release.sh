#!/bin/bash 
BASEDIR=`dirname $0`

AUTHORS="Thomas Qvarnstrom, Red Hat, @tqvarnst"
OPENSHIFT_DOMAIN=jbossdemocentral
GITHUB_REPO_HTTP_URL=https://github.com/jbossdemocentral/site.git



function print_header() {
	# wipe screen.
	clear 
	echo

	ASCII_WIDTH=56

	printf "##  %-${ASCII_WIDTH}s  ##\n" | sed -e 's/ /#/g'
	printf "##  %-${ASCII_WIDTH}s  ##\n"   
	printf "##  %-${ASCII_WIDTH}s  ##\n"
	printf "##  %-${ASCII_WIDTH}s  ##\n" "    # ####   ###   ###  ###"
	printf "##  %-${ASCII_WIDTH}s  ##\n" "    # #   # #   # #    #   "
	printf "##  %-${ASCII_WIDTH}s  ##\n" "    # ####  #   #  ##   ## "
	printf "##  %-${ASCII_WIDTH}s  ##\n" "#   # #   # #   #    #    #"
	printf "##  %-${ASCII_WIDTH}s  ##\n" " ###  ####   ###  ###  ### "  
	printf "##  %-${ASCII_WIDTH}s  ##\n"
	printf "##  %-${ASCII_WIDTH}s  ##\n"
	printf "##  %-${ASCII_WIDTH}s  ##\n"   
	printf "##  %-${ASCII_WIDTH}s  ##\n" "brought to you by,"
	printf "##  %-${ASCII_WIDTH}s  ##\n" "  ${AUTHORS}"
	printf "##  %-${ASCII_WIDTH}s  ##\n"
	printf "##  %-${ASCII_WIDTH}s  ##\n"
	printf "##  %-${ASCII_WIDTH}s  ##\n" | sed -e 's/ /#/g'
}

function print_usage() {
	echo "This a release script is for releasing the jbossdemocentral application to openshift"
	echo "usage: $0 [deploy <app-name>]" >&2	
	echo "    deploy - will deploy the application server. If it already exists it will delete it first"
}

function list_gears() {
	rhc apps
}

function deploy_openshift_app() {
	APP=$1
	BRANCH=$2
	SIZE=$3
	
	rhc app show $APP -n $OPENSHIFT_DOMAIN > /dev/null 2>&1
	if [ $? -eq 0 ]; then
		# App exists 
	    echo " - app $app exists, deleting it"
		rhc app-delete $APP -n $OPENSHIFT_DOMAIN --confirm
	fi
	# App does not exist
	echo " - creating app $APP in domain $OPENSHIFT_DOMAIN"
	rhc app-create ${APP} jbosseap-6 \
		--env GITHUB_API_CLIENT_ID=${GITHUB_API_CLIENT_ID} GITHUB_API_SECRET=${GITHUB_API_SECRET_ENV_VAR} \
		-n ${OPENSHIFT_DOMAIN} \
		--from-code ${GITHUB_REPO_HTTP_URL}#${BRANCH} \
		--gear-size ${SIZE} \
		--no-git
	echo " - succesfully created app. You can access it here: http://$APP-$OPENSHIFT_DOMAIN.rhcloud.com"	
}

function get_github_oauth_env() {
	if [ -r $BASEDIR/setgithuboauthenv.sh ]; then
		source $BASEDIR/setgithuboauthenv.sh	
	else
		echo "ERROR: Failed to source setgithuboauthenv.sh. This file must exists in $BASEDIR and have read permission for the current user. Please use setgithuboauthenv.template as template"
		exit 1
	fi	
}



print_header
get_github_oauth_env

if [ $# -eq 0 ]; then
	echo "$0 requires an argument"
	print_usage
	exit 2
fi


case "$1" in
deploy)
	APP=$2
	SIZE=$3
	BRANCH=$4
	if [ -z "${APP}" ]; then
    	echo "ERROR: You must specify app-name"
		print_usage
    	exit 3
	fi
	if [ -z "${SIZE}" ]; then
    	echo "ERROR: You must specify a size"
		print_usage
    	exit 4
	fi
	if [ -z "${BRANCH}" ]; then
    	echo "WARNING: No branch specified, will use master as branch"
		BRANCH=master
	fi
	deploy_openshift_app ${APP} ${BRANCH} ${SIZE}
	;;
list)
	list_gears
	;;
*)
	print_usage
	exit 2
	;;
esac



