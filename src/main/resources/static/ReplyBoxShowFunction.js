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