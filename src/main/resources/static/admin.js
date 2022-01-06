getUserTable()
function getUserTable() {
    fetch('http://localhost:8080/api/users').then(res => {
        res.json().then(data => {
            if (data.length > 0) {
                let temp = ""
                // start for loop

                data.forEach((u) => {
                    temp += '<tr>'
                    temp += '<td>' + u.id + '</td>'
                    temp += '<td>' + u.username + '</td>'
                    temp += '<td>' + u.password + '</td>'
                    temp += '<td>' + u.email + '</td>'
                    temp += '<td>' + u.city + '</td>'
                    temp += '<td>' + u.roles.map(r => r.role) + '</td>'
                    temp += '<td><button onclick="getUserById(' + u.id + ')" class="btn btn-info edit-btn"  data-toggle="modal" data-target="#editModal">Edit</button></td>'
                    temp += '<td><button onclick="getUserById(' + u.id + ')" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">Delete</button></td>'
                    temp += '</tr>'
                })

                // close for loop

                document.getElementById('all_users_table').innerHTML = temp
            }
        })
    })
}
function getUserById(id) {
    fetch("/api/users/" + id, {method: 'GET', dataType: 'json',})
        .then(res => {
            res.json().then(user => {
                $('#modalId').val(user.id)
                $('#modalUsername').val(user.username)
                $('#modalEmail').val(user.email)
                $('#modalPassword').val(user.password)
                $('#modalCity').val(user.city)
                user.roles.map(role => {
                    $('#modalRoles').append('<option id="' + role.id + '" name="' + role.role + '">' +
                        role.role + '</option>')
                })
            })
        })
}
function editUser(id) {

}

function deleteUser() {
fetch("/api/users/"+ ($('#modalId').val()),{method:"DELETE"})
    .then(()=>{
       $('#all_users_table').empty()
       getUserTable()
       ($("#deleteModal .close").ariaModal('hide')).click();
        document.getElementById("deleteUserForm").reset();
        $('#modalRoles > option').remove();
    })
}

function stop () {
    createNewUser()


}
let createNewUserButton = document.querySelector('#crNewUserButton')
createNewUserButton.onclick = createNewUser()


 function createNewUser() {
    // let user = {
    //     username: document.getElementById('newUsername').value,
    //     password: document.getElementById('newPassword').value,
    //     email: document.getElementById('newEmail').value,
    //     city: document.getElementById('newCity').value,
    //     roles: Array.from(document.getElementById('newRoles').selectedOptions).map(role => role.value)
    // }
    //
    //  fetch('http://localhost:8080/api/users', {
    //     method: 'POST',
    //     headers: {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json;charset=utf8'
    //     }, body: JSON.stringify(user)})
    //     .then(()=>{
    //     getUserTable()
    //     document.getElementById("newUserForm").reset()
    // })

     let username = document.getElementById('newUsername').value;
     let password = document.getElementById('newPassword').value;
     let email = document.getElementById('newEmail').value;
     let city= document.getElementById('newCity').value;
     let roles = getRoles(Array.from(document.getElementById('newRoles').selectedOptions)
         .map(role => role.value));
     fetch("/api/users", {
         method: "POST",
         headers: {
             'Accept': 'application/json',
             'Content-Type': 'application/json;charset=UTF-8'
         },
         body: JSON.stringify({
             username: username,
             password: password,
             email: email,
             city: city,
             roles: roles
         })
     })
         .then(() => {
             getUserTable();
             document.getElementById("newUserForm").reset();
         })
}
function getRoles(list) {
    let roles = [];
    if (list.indexOf("USER") >= 0) {
        roles.push({"id": 2});
    }
    if (list.indexOf("ADMIN") >= 0) {
        roles.push({"id": 1});
    }
    return roles;
}