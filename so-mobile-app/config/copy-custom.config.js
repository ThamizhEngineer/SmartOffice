const existingConfig = require('../node_modules/@ionic/app-scripts/config/copy.config');
module.exports = Object.assign(existingConfig, {
    copyFontawesomeFonts: {
      src: ['{{ROOT}}/node_modules/@fortawesome/fontawesome-free/webfonts/**/*'],
      dest: '{{WWW}}/assets/icons/fontawesome-free/webfonts'
    },
    copyFontawesomeCss: {
      src: ['{{ROOT}}/node_modules/@fortawesome/fontawesome-free/css/all.min.css'],
      dest: '{{WWW}}/assets/icons/fontawesome-free/css'
    },
    copySimpleLineIconsFonts: {
      src: ['{{ROOT}}/node_modules/simple-line-icons/fonts/**/*'],
      dest: '{{WWW}}/assets/icons/simple-line-icons/fonts'
    },
    copySimpleLineIconsCss: {
      src: ['{{ROOT}}/node_modules/simple-line-icons/css/simple-line-icons.css'],
      dest: '{{WWW}}/assets/icons/simple-line-icons/css'
    },
    copyMaterialDesignIconicFonts: {
      src: ['{{ROOT}}/node_modules/material-design-iconic-font/dist/fonts/**/*'],
      dest: '{{WWW}}/assets/icons/material-design-iconic-font/fonts'
    },
    copyMaterialDesignIconicCss: {
      src: ['{{ROOT}}/node_modules/material-design-iconic-font/dist/css/material-design-iconic-font.min.css'],
      dest: '{{WWW}}/assets/icons/material-design-iconic-font/css'
    },
    copyWeatherIconsFonts: {
      src: ['{{ROOT}}/node_modules/weather-icons/font/**/*'],
      dest: '{{WWW}}/assets/icons/weather-icons/fonts'
    },
    copyWeatherIconsCss: {
      src: ['{{ROOT}}/node_modules/weather-icons/css/weather-icons.min.css'],
      dest: '{{WWW}}/assets/icons/weather-icons/css'
    },
    copyPoppinsFonts: {
      src: ['{{ROOT}}/node_modules/wfk-poppins/fonts/**/*'],
      dest: '{{WWW}}/assets/fonts/poppins/fonts'
    },
    copyPoppinsCss: {
      src: ['{{ROOT}}/node_modules/wfk-poppins/poppins.css'],
      dest: '{{WWW}}/assets/fonts/poppins'
    }
});
