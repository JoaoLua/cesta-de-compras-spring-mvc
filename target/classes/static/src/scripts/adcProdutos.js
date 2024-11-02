const containerProdutos = document.querySelector('.produto-container')
let cart = []; // Array para armazenar os itens do carrinho
// Caminho do arquivo JSON
fetch('src/scripts/produtos.json')
  .then(response => response.json())
  .then(produtos => {
    
    produtos.forEach(produto => {

      const produtoDiv = document.createElement('div')
      produtoDiv.classList.add('produto')

      produtoDiv.innerHTML = `
        <img src="${produto.imagem}" alt="${produto.nome}">
        <p>${produto.nome}</p>
        <p>R$ ${produto.preco.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
        <button class="bn1" id="adcCart">Adicionar ao carrinho</button>
        <button class="bn1" id="ver-produto">Ver Produto</button>
      `
      const verProdutoBtn = produtoDiv.querySelector('#ver-produto');
      verProdutoBtn.addEventListener('click', () => {
        // Redireciona para detail.html com o ID do produto
        window.location.href = `detail.html?id=${produto.id}`;
      });

      const adcCartBtn = produtoDiv.querySelector('#adcCart');
      adcCartBtn.addEventListener('click', () => {
        addToCart(produto); // Chama a função para adicionar ao carrinho
      });

      containerProdutos.appendChild(produtoDiv)
    })
  })
    .catch(error => {
    console.error('Erro ao carregar o arquivo JSON:', error);
  });

  // Função para adicionar o produto ao carrinho
function addToCart(produto) {
  const existingProductIndex = cart.findIndex(item => item.id === produto.id);

  if (existingProductIndex >= 0) {
    // Se o produto já está no carrinho, aumenta a quantidade
    cart[existingProductIndex].quantity += 1;
  } else {
    // Se não, adiciona o produto ao carrinho
    cart.push({
      ...produto,
      quantity: 1
    });
  }

  // Atualiza o carrinho visualmente
  updateCart();
}

function updateCart() {
  const listCart = document.querySelector('.listCart');

  // Verificar se o elemento listCart existe no DOM
  if (!listCart) {
    console.error('O elemento .listCart não foi encontrado no DOM.');
    return;
  }

  listCart.innerHTML = ''; // Limpa o conteúdo anterior do carrinho

  cart.forEach(item => {
    const cartItem = document.createElement('div');
    cartItem.classList.add('itemCart');

    cartItem.innerHTML = `
      <div id="imgCart">
        <img src="${item.imagem}" alt="${item.nome}">
      </div>
      <div id="cartName">
        ${item.nome}
      </div>
      <div id="totalPice">
        ${(item.preco * item.quantity).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
      </div>
      <div id="quantity">
        <span id="minus" data-id="${item.id}">-</span>
        <span>${item.quantity}</span>
        <span id="plus" data-id="${item.id}">+</span>
      </div>
    `;

    // Adicionar eventos de aumentar/diminuir quantidade
    const minusBtn = cartItem.querySelector('#minus');
    minusBtn.addEventListener('click', () => {
      updateQuantity(item.id, 'minus');
    });

    const plusBtn = cartItem.querySelector('#plus');
    plusBtn.addEventListener('click', () => {
      updateQuantity(item.id, 'plus');
    });

    listCart.appendChild(cartItem);
  });
}

function updateQuantity(produtoId, action) {
  const productIndex = cart.findIndex(item => item.id == produtoId);

  if (productIndex >= 0) {
    if (action === 'minus') {
      cart[productIndex].quantity -= 1;
      // Se a quantidade do item chegar a 0, removemos o item do carrinho
      if (cart[productIndex].quantity === 0) {
        cart.splice(productIndex, 1); // Remove o item do array
      }
    } else if (action === 'plus') {
      cart[productIndex].quantity += 1;
    }
  }

  // Atualiza o carrinho no HTML
  updateCart();
}