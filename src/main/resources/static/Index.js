const deletebuttons = document.querySelectorAll(".delete-button-index");
deletebuttons.forEach(function (button) {
    const threadID = button.attributes.getNamedItem("data-threadID").value;
    button.addEventListener("click", function () {
        if (confirm("Do you want to delete thread: " + threadID)) {
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

            xhr.open('POST', '/m/delete/thread/' + threadID, true);
            xhr.setRequestHeader(header, token);
            xhr.send();
        }
    });
});
