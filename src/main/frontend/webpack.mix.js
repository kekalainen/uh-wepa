const mix = require('laravel-mix');
const tailwindcss = require('tailwindcss');

mix.disableNotifications();

mix.setPublicPath('../resources/static');

mix.webpackConfig({
    output: {
        chunkFilename: 'js/chunks/[name].js'
    }
});

mix.js('src/js/index.js', 'js')
    .sass('src/scss/app.scss', 'css')
    .options({
        processCssUrls: false,
        postCss: [tailwindcss('./tailwind.config.js')],
    })
    .version();
