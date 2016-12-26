import $ from 'jquery'

export default {
    submit(user, password, suc, err) {
        var data = {}
        data.user = user
        data.password = password
        $.post('http://localhost.ms.com:8090/login', data).done(suc).fail(err)
        $.get('http://localhost.ms.com:8090/echo')
    }
} 
