$(async function() {
    await thisUser();
});
async function thisUser() {
    fetch("http://localhost:8088/admin/viewUser")
        .then(res => res.json())
        .then(data => {
            // Добавляем информацию в шапку
            $('#headerUsername').append(data.username);
            let roles = data.roles.map(role => " " + role.role.substring(5));
            $('#headerRoles').append(roles);

            //Добавляем информацию в таблицу
            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.lastName}</td>
                <td>${data.age}</td>
                <td>${data.username}</td>
                <td>${data.password}</td>
                
                <td>${roles}</td>)`;
            $('#userPanelBody').append(user);
        })
}