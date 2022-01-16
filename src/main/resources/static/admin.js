let allRoles = [];
//загрузка всех ролей
getRoles();
//загрузка текущего пользователя
getPrincipal();
// загрузка пользователей
getUsers();

// Получение всех пользователей
async function getUsers() {
    // отправляет запрос и получаем ответ
    const response = await fetch("/api/users", {
        method: "GET",
        headers: {"Accept": "application/json"}
    });
    // если запрос прошел нормально
    if (response.ok === true) {
        // получаем данные
        const users = await response.json();
        let rows = document.querySelector("#all_users_table");
        users.forEach(user => {
            // добавляем полученные элементы в таблицу
            rows.append(row(user));
        });
    }
}

// Получение текущего пользователя
async function getPrincipal() {
    // отправляет запрос и получаем ответ
    const response = await fetch("/api/principal", {
        method: "GET",
        headers: {"Accept": "application/json"}
    });
    // если запрос прошел нормально
    if (response.ok === true) {
        // получаем данные
        const user = await response.json();
        console.log(user);
        $('#header-email').append(user.email);
        let principalRoles = user.roles
            .map(role => role.role)
            .map(s => s.substring(s.indexOf('_') + 1) + " ")
        $('#header-roles').append(principalRoles);
    }
}

// Получение всех ролей
async function getRoles() {
    // отправляет запрос и получаем ответ
    const response = await fetch("/api/roles", {
        method: "GET",
        headers: {"Accept": "application/json"}
    });
    // если запрос прошел нормально
    if (response.ok === true) {
        // получаем данные
        const roles = await response.json();

        document.getElementById('newRoles')
        let newOption;
        roles.map(role => {
            newOption = new Option(role.role, role.role);
            newRoles.append(newOption);
        });
    }
}

// Получение одного пользователя для редактирования
async function editFormUser(id) {
    const response = await fetch("/api/users/" + id, {
        method: "GET",
        headers: {"Accept": "application/json"}
    });
    if (response.ok === true) {
        const user = await response.json();
        const form = document.forms.editUserForm;
        form.eId.value = user.id;
        form.elements["eUsername"].value = user.username;
        form.elements["ePassword"].value = user.password;
        form.elements["eEmail"].value = user.email;
        form.elements["eCity"].value = user.city;
        // form.elements["eNameRoles"].value = user.roles.map(role => {
        //     form.elements["eNameRoles"].option = new Option(role, role);
        // });
        let newOption;
        user.roles.map(role => {
            newOption = new Option(role.role, role.role);
            editRoles.append(newOption);
        });
    }
}

// Получение одного пользователя для удаления
async function deleteFormUser(id) {
    const response = await fetch("/api/users/" + id, {
        method: "GET",
        headers: {"Accept": "application/json"}
    });
    if (response.ok === true) {
        const user = await response.json();
        const form = document.forms.deleteUserForm;
        form.dId.value = user.id;
        form.elements["dUsername"].value = user.username;
        form.elements["dPassword"].value = user.password;
        form.elements["dEmail"].value = user.email;
        form.elements["dCity"].value = user.city;
        let newOption;
        user.roles.map(role => {
            newOption = new Option(role.role, role.role);
            modalRoles.append(newOption);
        });
    }
}

// Добавление пользователя
async function createUser(username, password, email, city, roles) {

    const response = await fetch("api/users", {
        method: "POST",
        headers: {"Accept": "application/json", "Content-Type": "application/json"},
        body: JSON.stringify({
            username: username,
            email: email,
            city: city,
            roles: roles,
            password: password,
        })
    });
    if (response.ok === true) {
        const user = await response.json();
        reset("newUserForm");
        document.querySelector("#all_users_table").append(row(user));
    }
}

// Изменение пользователя
async function editUser(eId, eName, ePassword, eEmail, eCity, eRoles) {
    const response = await fetch("api/users", {
        method: "PUT",
        headers: {"Accept": "application/json", "Content-Type": "application/json"},
        body: JSON.stringify({
            id: eId,
            username: eName,
            password: ePassword,
            email: eEmail,
            city: eCity,
            roles: eRoles
        })
    });
    if (response.ok === true) {
        const user = await response.json();
        reset();
        document.querySelector("tr[data-rowid='" + user.id + "']").replaceWith(row(user));
    }
}

// Удаление пользователя
async function deleteUser(id) {
    const response = await fetch("/api/users/" + id, {
        method: "DELETE",
        headers: {"Accept": "application/json"}
    });
    if (response.ok === true) {
        const user = await response.json();
        document.querySelector("tr[data-rowid='" + user.id + "']").remove();
    }
}

// сброс формы
function reset(formName) {
    const form = document.forms[formName];
    form.reset();
    form.elements["id"].value = 0;
}

// создание строки для таблицы
function row(user) {

    const tr = document.createElement("tr");
    tr.setAttribute("data-rowid", user.id);

    const idTd = document.createElement("td");
    idTd.append(user.id);
    tr.append(idTd);

    const usernameTd = document.createElement("td");
    usernameTd.append(user.username);
    tr.append(usernameTd);

    const passwordTd = document.createElement("td");
    passwordTd.append(user.password);
    tr.append(passwordTd);

    const emailTd = document.createElement("td");
    emailTd.append(user.email);
    tr.append(emailTd);

    const cityTd = document.createElement("td");
    cityTd.append(user.city);
    tr.append(cityTd);

    const rolesTd = document.createElement("td");
    rolesTd.append(user.roles.map(role => role.role)
        .map(s => s.substring(s.indexOf('_') + 1) + " "));
    tr.append(rolesTd);

    const editTd = document.createElement("td");

    const editButton = document.createElement("button");
    editButton.setAttribute("data-toggle", "modal");
    editButton.setAttribute("data-target", "#editModal");
    editButton.setAttribute("data-id", user.id);
    editButton.setAttribute("class", "btn btn-info edit-btn");
    editButton.append("Изменить");
    editButton.addEventListener("click", e => {

        e.preventDefault();
        editFormUser(user.id);
    });
    editTd.append(editButton);
    tr.append(editTd);

    const deleteTd = document.createElement("td");

    const deleteButton = document.createElement("button");
    deleteButton.setAttribute("data-toggle", "modal");
    deleteButton.setAttribute("data-target", "#deleteModal");
    deleteButton.setAttribute("data-id", user.id);
    deleteButton.setAttribute("class", "btn btn-danger");
    deleteButton.append("Удалить");
    deleteButton.addEventListener("click", e => {

        e.preventDefault();
        deleteFormUser(user.id);
    });
    deleteTd.append(deleteButton);
    tr.append(deleteTd);

    return tr;
}

// // сброс значений формы
// document.getElementById("reset").click(function (e) {
//
//     e.preventDefault();
//     reset();
// })

document.getElementById('myTab').addEventListener("profile-tab", e => {
    e.preventDefault();
    getRoles();
    const form = document.forms["newUserForm"];
    form.elements["newRoles"].value = allRoles;

})
// отправка формы нового пользователя
document.forms["newUserForm"].addEventListener("crNewUserButton", e => {
    e.preventDefault();
    const form = document.forms["newUserForm"];
    const username = form.elements["newUsername"].value;
    const password = form.elements["newPassword"].value;
    const email = form.elements["newEmail"].value;
    const city = form.elements["newCity"].value;
    const roles = Array.from(document.getElementById('newRoles').selectedOptions)
        .filter(option => option.selected)
        .map(option => option.value);
    console.log(createUser(username, password, email, city, roles))
    createUser(username, password, email, city, roles);
    reset("newUserForm");
});
// отправка формы изменения пользователя
document.forms["editUserForm"].addEventListener("updateButton", e => {
    e.preventDefault();
    const eform = document.forms["editUserForm"];
    const eid = eform.elements["eId"].value;
    const eusername = eform.elements["eUsername"].value;
    const epassword = eform.elements["ePassword"].value;
    const eemail = eform.elements["eEmail"].value;
    const ecity = eform.elements["eCity"].value;
    const eroles = eform.elements["eNameRoles"].value;

    editUser(eid, eusername, epassword, eemail, ecity, eroles);
    reset();
});
