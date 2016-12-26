import $ from "jquery";

export function LoginService (user, password, success, error) {
        data = {}
        data.user = user;
        data.password = password;
        $.post('/ap', data).done(success).fail(error)
    }



