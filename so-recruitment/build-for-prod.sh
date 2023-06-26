#!/bin/sh

rm -rf dist recruitment
rm recruitment.tar.gz
node --max_old_space_size=1096 ./node_modules/@angular/cli/bin/ng build --prod --base-href "/recruitment/" --deploy-url "/recruitment/"
# in local you could run this command instead of the above
#ng build --prod --base-href "/smart-office/" --deploy-url "/smart-office/"
mv dist recruitment
tar -czvf recruitment.tar.gz recruitment