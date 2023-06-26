#!/bin/sh

NGINX_LOC=/Applications/MAMP/htdocs/
rm -rf dist recruitment $NGINX_LOC/recruitment
rm recruitment.tar.gz
node --max_old_space_size=1096 ./node_modules/@angular/cli/bin/ng build --prod --base-href "/smart-office/" --deploy-url "/smart-office/"
# in local you could run this command instead of the above
#ng build --prod --base-href "/smart-office/" --deploy-url "/smart-office/"
mv dist recruitment
mv recruitment $NGINX_LOC/
