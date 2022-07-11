new Vue({
    el: '#izmeni-sadrzaj-app',
    data: 
	{
		staroIme:'',
		trening:[],
		treneri:[],
		sadrzaj: [],
        naziv: '',
        tip: '',
		sportskiobjekatid: '',
		trajanje: '',
		trenerid: '',
		opis: '',
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
            .post('app/getTreningByTreningId', jwt, 
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt'),
						'TreningId': window.localStorage.getItem('treningid')
                    }
                })
            .then(response => 
			{
                this.trening = response.data;
				this.staroIme = this.trening.naziv;
				console.log(this.staroIme);
				this.naziv = this.trening.naziv;
				this.tip= this.trening.tip;
				this.trenerid = this.trening.trener.korisnickoIme;
				this.opis = this.trening.opis;
            })
            .catch(error => 
			{
                console.log(error);
                alert("Problem sa ucitavanjem treninga!");
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
			
			console.log(this.staroIme);
			
			let imaVecToIme = false;
			let si = this.staroIme;
			this.sadrzaj.forEach(function (arrayItem) 
			{
   					if(this.naziv.value == arrayItem.naziv)
					{
						
							imaVecToIme = true;
						
					}
			});
				
			if(si = this.$refs.naziv)
			{
				imaVecToIme=false;
			}
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
                this.izmeniSadrzaj();
            }
        
    	},
    	
    	izmeniSadrzaj: function()
		{

    		let putanja = 'app/izmeniTrening';
			let jwt =  window.localStorage.getItem('jwt');
            axios
                .post(putanja, jwt,
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt'),
						'id': window.localStorage.getItem('treningid'),
						'naziv': this.naziv,
		        		'tip': this.tip,
						'trenerid': this.trenerid,
						'opis': this.opis
                    }
                })
                .then(response => 
				{
                    alert(response.data);
					window.location = "sadrzaj.html";
                })
                .catch(error => 
				{
                    console.log(error);
                    this.$refs.err_msg.classList.add("error-msg");
                    this.$refs.err_msg.innerHTML = error.response.data.sadrzaj;
                });
    		
        }
    }
    

       
});
