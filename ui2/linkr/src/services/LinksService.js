import $ from 'jquery'

export default {
    submit(user, link, suc, err) {
        var encodedLink = window.btoa(link)   
        var base = 'http://localhost:8090/links/' + user + '/' + encodedLink
        $.ajax({
            type: 'GET',
            url: base
        }).done(suc).fail(err)

        // $.post('http://localhost.ms.com:8090/login', JSON.stringify(data), null, 'json').done(suc).fail(err)
    },

    getLinksForUser(user,suc, err) {
        var base = 'http://localhost:8090/user/' + user + '/links'
         $.ajax({
            type: 'GET',
            url: base
        }).done(suc).fail(err)
    }

} 
