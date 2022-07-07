new Vue({
    el: '#dodaj-sadrzaj-app',
    data: 
	{
		treneri:[],
		sadrzaj: [],
        naziv: '',
        tip: '',
		sportskiobjekatid: '',
		trajanje: '',
		trenerid: '',
		opis: '',
		slika:'',
        sveDobro: true
    },
	mounted()   
	{
      	this.uloga = window.localStorage.getItem('uloga');

        if (this.uloga == null || this.uloga != 1) 
		{
            alert('Sta se pravis pametan!');
            window.localStorage.removeItem('uloga');
            window.localStorage.removeItem('jwt');
			window.location = "index.html";
        }

		let jwt = window.localStorage.getItem('jwt');
		
		 axios
            .get('app/getTreneri')
            .then(response => 
			{
                this.treneri = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                alert("Problem sa ucitavanjem trenera!");
            });

        axios
            .post('app/getTreningeZaObjekat', jwt, 
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')
                    }
                })
            .then(response => 
			{
                this.sadrzaj = response.data;
				
            })
            .catch(error => 
			{
                console.log(error);
                alert("Problem sa ucitavanjem treninga!");
            });
    },
    methods: 
	{
    	provera: function() 
		{
			
			
            sveDobro = true;
            
            if (this.$refs.tip == "") 
			{
                this.$refs.tip.classList.remove("is-valid");
                this.$refs.tip.classList.add("is-invalid");
                sveDobro = false;
            } 
			else 
			{
                if (this.$refs.tip.classList.contains('is-invalid'))    
				{
                    this.$refs.tip.classList.remove("is-invalid");
                    this.$refs.tip.classList.add("is-valid");
                }
            }
			
			
			let imaVecToIme = false;
			this.sadrzaj.forEach(function (arrayItem) 
			{
   					if(this.naziv.value == arrayItem.naziv)
					{
						imaVecToIme = true;
					}
			});
				
			if (this.$refs.naziv == "" || imaVecToIme) 
			{
				alert("Ne moze taj naziv");
                this.$refs.naziv.classList.remove("is-valid");
                this.$refs.naziv.classList.add("is-invalid");
                sveDobro = false;
            } 
			else 
			{
                if (this.$refs.naziv.classList.contains('is-invalid'))    
				{
                    this.$refs.naziv.classList.remove("is-invalid");
                    this.$refs.naziv.classList.add("is-valid");
                }
            }
            
            if(sveDobro) 
			{
                this.dodajSadrzaj();
            }
        
    	},
    	
    	dodajSadrzaj: function()
		{
    		var trening = 
			{
				'naziv': this.naziv,
        		'tip': this.tip,
				'sportskiobjekatid': this.sportskiobjekatid,
				'trajanje': this.trajanje,
				'trenerid': this.trenerid,
				'opis': this.opis,
				'slika': this.slika
    		};

    		let putanja = 'app/dodajTrening';
			
            axios
                .post(putanja, trening, 
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')
                    }
                })
                .then(response => 
				{
                    this.$refs.err_msg.classList.remove("error-msg");
                    this.$refs.err_msg.classList.add("ok-msg");
                    this.$refs.err_msg.innerHTML = response.data.sadrzaj;

                    if (this.fajl != null)   
					{
    					console.log(response.headers);
                        let formData = new FormData();
                        formData.append('file', this.fajl);

                        let id = response.headers['idnovogtreninga'];
                        axios
                            .post('app/dodajSlikuTreningu', formData, 
							{
                                headers: 
								{
                                    'Content-Type': 'multipart/form-data',
                                    'Autorizacija': window.localStorage.getItem('jwt'),
                                    'idtreninga': id
                                }
                            })
                            .then(response => 
							{
                                this.sportskiObjekat = response.data;
								window.location = "sadrzaj.html";
                            })
                            .catch(error => 
							{
                                console.log(error);
                                alert(error.response.data.sadrzaj);
                            });
                    }
					else
					{
						console.log("Nemam sliku!");
					}
                })
                .catch(error => 
				{
                    console.log(error);
                    this.$refs.err_msg.classList.add("error-msg");
                    this.$refs.err_msg.innerHTML = error.response.data.sadrzaj;
                });
    		
        },
        
        preuzmiSliku: function() 
		{
            this.fajl = this.$refs.slika.files[0];
        }
    
    }
    

       
});
