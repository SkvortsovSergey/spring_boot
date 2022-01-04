async function con() {
    response = await fetch('http://localhost:8080/api/users').catch()

    let json
    if (response.ok) {
        json = await response.json()
    } else {
        alert('Ошибка HTTP:' + response.status)
    }
    console.log(json)
}
con()