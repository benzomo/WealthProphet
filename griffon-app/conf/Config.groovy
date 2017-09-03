application {
    title = 'wealth-prophet'
    startupGroups = ['wealthProphet']
    autoShutdown = true
}
mvcGroups {
    // MVC Group for "wealthProphet"
    'wealthProphet' {
        model      = 'WP.WealthProphetModel'
        view       = 'WP.WealthProphetView'
        controller = 'WP.WealthProphetController'
    }
}