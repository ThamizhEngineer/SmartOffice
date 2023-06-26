// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
    production: false,
	authUrl: "http://192.168.1.15:8000/so-auth-service/",
	clientId: "pothigai-power",
    apiUrl: 'http://localhost/ci_api/api/',
    xapikey: 'ABCDE',
    serviceApiUrl:"http://192.168.1.15:8000/so-service/",
    // serviceApiUrl:"http://localhost:6005/so-service/",
    documentApiUrl:"http://192.168.1.15:8000/so-document-service/",
    // serviceApiUrl:"http://localhost:4000/so-service/",
    healthCheckUrl:"http://192.168.1.15:8000/healthcheck.json", 
    assetsUrl:"http://localhost:4200/",
    applicationCode:"recruitment"
};
