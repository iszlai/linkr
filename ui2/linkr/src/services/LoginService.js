import $ from 'jquery'

export default {
    submit(user, password, suc, err) {
        var data = {}
        data.name = user
        data.password = password

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8090/login',
            data: JSON.stringify(data),
            contentType: 'application/json'
        }).done(suc).fail(err)

        // $.post('http://localhost.ms.com:8090/login', JSON.stringify(data), null, 'json').done(suc).fail(err)
        $.get('http://localhost:8090/echo')
    }
} 
