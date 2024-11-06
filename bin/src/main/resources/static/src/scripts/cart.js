let iconCart = document.querySelector("#cart")
let body = document.querySelector("body")
const closeCart = document.querySelector("#cartClose")

iconCart.addEventListener("click", () => {
    body.classList.toggle("showCart")

})

closeCart.addEventListener("click", () => {
    body.classList.remove("showCart")
})