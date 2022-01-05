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
                temp += '<td><button onclick="editUserById(' + u.id + ')" class="btn btn-info edit-btn"  data-toggle="modal" data-target="#editModal">Edit</button></td>'
                temp += '<td><button onclick="deleteUserById(' + u.id + ')" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">Delete</button></td>'
                temp += '</tr>'
            })

            // close for loop

            document.getElementById('all_users_table').innerHTML = temp
        }
    })
})

function editUserById(id) {
}

function deleteUserById(id) {

}

async function createNewUser() {
    let user = {
        username: document.getElementById('newUsername').value,
        password: document.getElementById('newPassword').value,
        email: document.getElementById('newEmail').value,
        city: document.getElementById('newCity').value,
        roles: Array.from(document.getElementById('newRoles').selectedOptions)
    }
    await fetch('http://localhost:8080/api/users', {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=utf8'
        }, body: JSON.stringify(user)
    })
}