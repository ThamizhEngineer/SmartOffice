#!/bin/sh

rm -rf dist smart-office
rm smart-office.tar.gz
node --max_old_space_size=6144 ./node_modules/@angular/cli/bin/ng build --output-hashing=all --prod --base-href "/smart-office/" --deploy-url "/smart-office/"
# in local you could run this command instead of the above
#ng build --prod --base-href "/smart-office/" --deploy-url "/smart-office/"
mv dist smart-office
tar -czvf smart-office.tar.gz smart-office
