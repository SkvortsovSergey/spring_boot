// Получение текущего пользователя, добавление иформации в хедер и заполнение таблицы
async function getPrincipal() {
    // отправляет запрос и получаем ответ
    const response = await fetch("/api/principal", {
        method: "GET",
        headers: {"Accept": "application/json"}
    });
    // если запрос прошел нормально
    if (response.ok === true) {
        // получаем данные
        const principal = await response.json();
        console.log(principal);
        $('#user-header-email').append(principal.email);
        let principalRoles = principal.roles
            .map(role => role.role)
            .map(s => s.substring(s.indexOf('_') + 1) + " ")
        $('#user-header-roles').append(principalRoles);
        // добавляем полученные элементы в таблицу
        document.querySelector("#principal-table").append(newRow(principal));
    }
}

getPrincipal();

// создание строки для таблицы
function newRow(user) {

    const tr = document.createElement("tr");

    const userIdTd = document.createElement("td");
    userIdTd.append(user.id);
    tr.append(userIdTd);

    const userUsernameTd = document.createElement("td");
    userUsernameTd.append(user.username);
    tr.append(userUsernameTd);

    const userEmailTd = document.createElement("td");
    userEmailTd.append(user.email);
    tr.append(userEmailTd);

    const userCityTd = document.createElement("td");
    userCityTd.append(user.city);
    tr.append(userCityTd);

    const userRolesTd = document.createElement("td");
    userRolesTd.append(user.roles.map(role => role.role)
        .map(s => s.substring(s.indexOf('_') + 1) + " "));
    tr.append(userRolesTd);

    return tr;
}