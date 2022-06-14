var app = new Vue({
	el: '#sportskiobjekti',
	data: {
		sportskiobjekti: null,
		title: "Prikaz sportskih objekata:",
		mode: "BROWSE",
		selectedProduct: {},
		error: ''
	},
	mounted() {
		axios.get('rest/sportskiobjekti')
			.then(response => (this.sportskiobjekti = response.data))
	},
	methods: {
		editProduct: function (product) {
			this.selectedProduct = { ...product }
			this.mode = 'EDIT'
		},
		showForm: function () {
			this.mode = 'CREATE'
			this.selectedProduct = { id: "-1", name: "", status: "", tipObjekta: "", sadrzaj: "", lokacija: "", prosecnaOcena: 0, radnoVreme: ""  }
		},
		createOrEditProduct: function (event) {
			this.error = ""
			if ((this.selectedProduct.name=="") || (this.selectedProduct.status=="") || (this.selectedProduct.tipObjekta=="") || (this.selectedProduct.sadrzaj=="") || (this.selectedProduct.lokacija=="") || (this.selectedProduct.prosecnaOcena==0) || (this.selectedProduct.radnoVreme=="")) {
				this.error = "Unesite vrednosti za sva polja!";
				event.preventDefault();
				return;
			}
			if (this.mode == 'CREATE') {
				axios.post('rest/sportskiobjekti', this.selectedProduct)
					.then((response) => {
						alert('Novi proizvod uspešno kreiran')
						this.mode = 'BROWSE'
						this.sportskiobjekti.push(response.data)
					})

			} else {
				axios.put('rest/sportskiobjekti/' + this.selectedProduct.id, this.selectedProduct)
					.then((response) => {
						alert('Proizvod je uspešno izmenjen ')
						this.mode = 'BROWSE'
						this.sportskiobjekti = this.sportskiobjekti.filter((p) => p.id !== this.selectedProduct.id)
						this.sportskiobjekti.push(response.data)
					})
			}

			event.preventDefault();
		},
		deleteProduct: function (product) {
			this.mode = 'BROWSE'
			axios.delete('rest/sportskiobjekti/' + product.id)
				.then(() => {
					alert('Proizvod je uspesno obrisan')
					this.sportskiobjekti = this.sportskiobjekti.filter((p) => p.id !== product.id)
				})
		}
	}
});

