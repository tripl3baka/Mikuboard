const deletebuttons = document.querySelectorAll(".delete-button-index");
deletebuttons.forEach(function (button) {
    const postID = button.attributes.getNamedItem("data-postID").value;
    button.addEventListener("click", function () {
        if (confirm("Do you want to delete post: " + postID)) {
            const xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // alert("Response: " + xhr.responseText);
                        location.reload();
                    } else {
                        alert("Response: " + xhr.status);
                    }
                }
            };

            const elementToken = document.querySelector('meta[name="_csrf"]');
            const token = elementToken?.getAttribute("content");
            const elementHeader = document.querySelector('meta[name="_csrf_header"]');
            const header = elementHeader?.getAttribute("content");

            xhr.open('POST', '/m/delete/' + postID, true);
            xhr.setRequestHeader(header, token);
            xhr.send();
        }
    });
});