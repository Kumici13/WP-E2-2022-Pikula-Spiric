new Vue({
    el: '#dodaj-sportski-objekat-app',
    data: 
	{
		ponedeljakEnabled:'true',
		ponedeljakStart:'01:00',
		ponedeljakEnd:'23:00',
		
		utorakEnabled:'true',
		utorakStart:'01:00',
		utorakEnd:'23:00',
		
		sredaEnabled:'true',
		sredaStart:'01:00',
		sredaEnd:'23:00',
		
		cetvrtakEnabled:'true',
		cetvrtakStart:'01:00',
		cetvrtakEnd:'23:00',
		
		petakEnabled:'true',
		petakStart:'01:00',
		petakEnd:'23:00',
		
		subotaEnabled:'true',
		subotaStart:'01:00',
		subotaEnd:'23:00',
		
		nedeljaEnabled:'true',
		nedeljaStart:'01:00',
		nedeljaEnd:'23:00',
		
		
		slobodniMenadzeri: [],
		menadzer:'',
        naziv: '',
        tipObjekta: '',
        geoDuzina: '',
        geoSirina: '',
        ulica: '',
        broj: '',
        mesto: '',
        postanskiBroj: '',
        logo: '',
        fajl: '',
        sveDobro: true
    },
	mounted()   
	{
      	this.uloga = window.localStorage.getItem('uloga');

        if (this.uloga == null || this.uloga != 2) 
		{
            alert('Sta se pravis pametan!');
            window.localStorage.removeItem('uloga');
            window.localStorage.removeItem('jwt');
			window.location = "index.html";
        }

        axios
            .get('app/getSlobodniMenadzeri')
            .then(response => 
			{
                this.slobodniMenadzeri = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                alert(error.response.data.sadrzaj);
            });
    },
    methods: 
	{
    	provera: function() 
		{
            sveDobro = true;
            
            if (this.$refs.tipObjekta == "") 
			{
                this.$refs.tip.classList.remove("is-valid");
                this.$refs.tip.classList.add("is-invalid");
                sveDobro = false;
            } 
			else 
			{
                if (this.$refs.tipObjekta.classList.contains('is-invalid'))    
				{
                    this.$refs.tipObjekta.classList.remove("is-invalid");
                    this.$refs.tipObjekta.classList.add("is-valid");
                }
            }
            
            if (this.$refs.ulica.value.length <= 1 || !/^[a-zA-Z0-9 ]+$/.test(this.$refs.ulica.value))   
			{
                this.$refs.ulica.classList.remove("is-valid");
                this.$refs.ulica.classList.add("is-invalid");
                sveDobro = false;
            } 
			else  
			{
                if (this.$refs.ulica.classList.contains('is-invalid'))    
				{
                    this.$refs.ulica.classList.remove("is-invalid");
                    this.$refs.ulica.classList.add("is-valid");
                }
            }
            
            if (this.$refs.broj.value.length < 1 || !/^[0-9]+$/.test(this.$refs.broj.value))   
			{
                this.$refs.broj.classList.remove("is-valid");
                this.$refs.broj.classList.add("is-invalid");
                sveDobro = false;
            } 	
			else  
			{
                if (this.$refs.broj.classList.contains('is-invalid'))    
				{
                    this.$refs.broj.classList.remove("is-invalid");
                    this.$refs.broj.classList.add("is-valid");
                }
            }
            
            if (this.$refs.mesto.value.length <= 1 || !/^[a-zA-Z ]+$/.test(this.$refs.mesto.value))   
			{
                this.$refs.mesto.classList.remove("is-valid");
                this.$refs.mesto.classList.add("is-invalid");
                sveDobro = false;
            } 
			else  
			{
                if (this.$refs.mesto.classList.contains('is-invalid'))    
				{
                    this.$refs.mesto.classList.remove("is-invalid");
                    this.$refs.mesto.classList.add("is-valid");
                }
            }
            
            if (this.$refs.postanskiBroj.value.length < 4 || this.$refs.postanskiBroj.value.length > 7 || !/^[0-9]+$/.test(this.$refs.postanskiBroj.value))   
			{
                this.$refs.postanskiBroj.classList.remove("is-valid");
                this.$refs.postanskiBroj.classList.add("is-invalid");
                sveDobro = false;
            } 
			else  
			{
                if (this.$refs.postanskiBroj.classList.contains('is-invalid'))    
				{
                    this.$refs.postanskiBroj.classList.remove("is-invalid");
                    this.$refs.postanskiBroj.classList.add("is-valid");
                }
            }

            if (this.$refs.geoSirina.value.length < 1 || !/^[0-9]+\.[0-9]+$/.test(this.$refs.geoSirina.value))   
			{
                this.$refs.geoSirina.classList.remove("is-valid");
                this.$refs.geoSirina.classList.add("is-invalid");
                sveDobro = false;
            } 
			else  
			{
                if (this.$refs.geoSirina.classList.contains('is-invalid'))    
				{
                    this.$refs.geoSirina.classList.remove("is-invalid");
                    this.$refs.geoSirina.classList.add("is-valid");
                }
            }
            
            if (this.$refs.geoDuzina.value.length < 1 || !/^[0-9]+\.[0-9]+$/.test(this.$refs.geoDuzina.value))   
			{
                this.$refs.geoDuzina.classList.remove("is-valid");
                this.$refs.geoDuzina.classList.add("is-invalid");
                sveDobro = false;
            } 
			else  
			{
                if (this.$refs.geoDuzina.classList.contains('is-invalid'))    
				{
                    this.$refs.geoDuzina.classList.remove("is-invalid");
                    this.$refs.geoDuzina.classList.add("is-valid");
                }
            }
            
            if(sveDobro) 
			{
                this.dodajSportskiObjekat();
            }
        
    	},
    	
    	dodajSportskiObjekat: function()
		{
    		let imenaDana = ['Ponedeljak', 'Utorak', 'Sreda', 'Cetvrtak', 'Petak', 'Subota', 'Nedelja'];
			let enejblovani = [this.ponedeljakEnabled, this.utorakEnabled, this.sredaEnabled, this.cetvrtakEnabled, this.petakEnabled, this.subotaEnabled, this.nedeljaEnabled];
			let startVremena = [this.ponedeljakStart, this.utorakStart, this.sredaStart, this.cetvrtakStart, this.petakStart, this.subotaStart, this.nedeljaStart];
			let endVremena = [this.ponedeljakEnd, this.utorakEnd, this.sredaEnd, this.cetvrtakEnd, this.petakEnd, this.subotaEnd, this.nedeljaEnd];
		
			let radnoVreme = [];

			for(let i = 0; i < 7;i++)
			{
				let dan = 
				{
					'imeDana': imenaDana[i],
					'danStart':startVremena[i],
					'danEnd': endVremena[i],
					'radniDan': enejblovani[i]
				}
				
				console.log(dan);
				radnoVreme.push(dan);
				
			}
    		var adresa = 
			{
    				'ulica' : this.ulica,
    				'broj' : this.broj,
    				'mesto' : this.mesto,
    				'postanskiBroj' : this.postanskiBroj
    		};
    		
    		var lokacija = 
			{
    				'geoSirina' : this.geoSirina,
    				'geoDuzina' : this.geoDuzina,
    				'adresa' : adresa
    		};
    		
    		var sportskiObjekat = 
			{
					'naziv'  : this.naziv,
					'status' : true,
    				'tipObjekta' : this.tipObjekta,
    				'sadrzaj' : ["/"],
    				'lokacija' : lokacija,
					'prosecnaOcena': 0,
					'radnoVreme': radnoVreme
    				
    		};
    		
    		let putanja = 'app/dodajSportskiObjekat';
			
            axios
                .post(putanja, sportskiObjekat, 
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt'),
						'Menadzer' : this.menadzer
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

                        let id = response.headers['idnovogsportskogobjekta'];
                        axios
                            .post('app/dodajSliku', formData, 
							{
                                headers: 
								{
                                    'Content-Type': 'multipart/form-data',
                                    'Autorizacija': window.localStorage.getItem('jwt'),
                                    'IdSportskogObjekta': id
                                }
                            })
                            .then(response => 
							{
                                this.sportskiObjekat = response.data;
								window.location = "sportskiObjekti.html";
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
            this.fajl = this.$refs.logo.files[0];
        },

		aktivirajTime: function (start, end)
		{
		  document.getElementById(start).disabled = !document.getElementById(start).disabled;
		  document.getElementById(end).disabled = !document.getElementById(end).disabled;
		},
		minTime: function (start, end)
		{
			if(document.getElementById(end).value<document.getElementById(start).value)
			{
				document.getElementById(end).value = document.getElementById(start).value
			}
		    document.getElementById(end).min = document.getElementById(start).value;
		}
    }
    

       
});
