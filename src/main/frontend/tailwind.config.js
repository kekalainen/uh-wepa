module.exports = {
    purge: {
        enabled: process.env.NODE_ENV === 'production',
        content: [
            '../resources/templates/**/*.html',
            './src/js/**/*.vue'
        ]
    },
    darkMode: false,
    theme: {
        extend: {
            maxWidth: {
                '16': '4rem'
            }
        }
    },
    variants: {
        extend: {}
    },
    plugins: []
}
