#!/bin/sh

NGINX_LOC=/opt/scrunch-work/common/data/nginx/html/
rm -rf dist smart-office $NGINX_LOC/smart-office
rm smart-office.tar.gz
node --max_old_space_size=6144 ./node_modules/@angular/cli/bin/ng build --prod --base-href "/smart-office/" --deploy-url "/smart-office/"
# in local you could run this command instead of the above
#ng build --prod --base-href "/smart-office/" --deploy-url "/smart-office/"
mv dist smart-office
mv smart-office $NGINX_LOC/
