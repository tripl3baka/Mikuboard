const targetDiv = document.getElementById("replyBox");
const btn = document.getElementById("replyButton");
targetDiv.style.display = "none";

btn.onclick = function () {
    if (targetDiv.style.display !== "none") {
        targetDiv.style.display = "none";
    } else {
        btn.style.display = "none";
        targetDiv.style.display = "block";
    }
};

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
console.log(urlParams.get('error'));

if(urlParams.get('error') === "true"){
        targetDiv.style.display = "block";
}

const deletebuttons = document.querySelectorAll(".delete-button-threadpage");
deletebuttons.forEach(function (button) {
    const replyID = button.attributes.getNamedItem("data-replyID").value;
    button.addEventListener("click", function () {
        if (confirm("Do you want to delete post: " + replyID)) {
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

            xhr.open('POST', '/m/delete/reply/' + replyID, true);
            xhr.setRequestHeader(header, token);
            xhr.send();
        }
    });
});

const threadDeleteButtons = document.querySelectorAll(".delete-button-index");
threadDeleteButtons.forEach(function (button) {
    const threadID = button.attributes.getNamedItem("data-threadID").value;
    button.addEventListener("click", function () {
        if (confirm("Do you want to delete thread: " + threadID)) {
            const xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // alert("Response: " + xhr.responseText);
                        location.replace("/m")
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
