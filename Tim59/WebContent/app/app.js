var app = new Vue({
	el: '#products',
	data: {
		products: null,
		title: "Prikaz sportskih objekata",
		mode: "BROWSE",
		selectedProduct: {},
		error: ''
	},
	mounted() {
		axios.get('rest/products')
			.then(response => (this.products = response.data))
	},
	methods: {
		editProduct: function (product) {
			this.selectedProduct = { ...product }
			this.mode = 'EDIT'
		},
		showForm: function () {
			this.mode = 'CREATE'
			this.selectedProduct = { id: '', name: null, status: null, type: null, sadrzaj: null, lokacija: null, ocena: null, vreme: null }
		},
		createOrEditProduct: function (event) {
			this.error = ""
			if (!this.selectedProduct.name || !this.selectedProduct.status || !this.selectedProduct.type || !this.selectedProduct.sadrzaj || !this.selectedProduct.lokacija || !this.selectedProduct.ocena || !this.selectedProduct.vreme) {
				this.error = "Morate popuniti sva polja!";
				event.preventDefault();
				return;
			}
			if (this.mode == 'CREATE') {
				axios.post('rest/products', this.selectedProduct)
					.then((response) => {
						alert('Novi proizvod uspešno kreiran')
						this.mode = 'BROWSE'
						this.products.push(response.data)
					})

			} else {
				axios.put('rest/products/' + this.selectedProduct.id, this.selectedProduct)
					.then((response) => {
						alert('Proizvod je uspešno izmenjen ')
						this.mode = 'BROWSE'
						this.products = this.products.filter((p) => p.id !== this.selectedProduct.id)
						this.products.push(response.data)
					})
			}

			event.preventDefault();
		},
		deleteProduct: function (product) {
			this.mode = 'BROWSE'
			axios.delete('rest/products/' + product.id)
				.then(() => {
					alert('Proizvod je uspesno obrisan')
					this.products = this.products.filter((p) => p.id !== product.id)
				})
		}
	}
});
