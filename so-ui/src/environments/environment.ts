// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
    production: false,
     authUrl: "http://172.16.16.102/so-auth-service/",
     clientId: "pothigai-power",
    apiUrl: 'http://172.16.16.102/ci_api/api/',
    xapikey: 'ABCDE',
    serviceApiUrl:"http://172.16.16.102/so-service/",
    reportApiUrl:"http://172.16.16.102/so-report-service/",
    notfnApiUrl:"http://172.16.16.102/so-notification-service/",
    // serviceApiUrl:"http://172.16.16.102:6005/so-service/",
    documentApiUrl:"http://172.16.16.102/so-document-service/",
    // serviceApiUrl:"http://172.16.16.102:4000/so-service/",
    chatApiUrl:"http://172.16.16.102/so-chat-service/",
    healthCheckServiceUrl:"http://172.16.16.102/so-health-check-application/health-check/startup",
    healthCheckUrl:"http://172.16.16.102/healthcheck.json",
    assetsUrl:"http://172.16.16.102:4200/",
    timeLeft: 600,
    sessExpire:3600,
    sessPopup:3000
 };
