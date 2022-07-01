new Vue({
    el: '#profilApp',
    data: {
        uloga: '',
        korisnik: {},
        starasifra: '',
        novasifra: '',
        novasifraponovo: ''
    },
    mounted()   
	{
		this.uloga = window.localStorage.getItem('uloga');
        
		if (this.uloga == null) 
		{
            alert("Morate se ulogovati!");
            window.localStorage.removeItem('uloga');
            window.localStorage.removeItem('jwt');
            window.location = 'login.html';
        }
        
		axios
            .get('app/getKorisnik', 
			{
                headers: 
				{	
					
                    'Autorizacija': window.localStorage.getItem('jwt')
                }
            })
            .then(response => 
			{
                this.korisnik = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                alert("Morate se ulogovati!");
                if (error.response.status == 400 || error.response.status == 403)   
				{
                    window.localStorage.removeItem('jwt');
                    window.location = 'login.html';
                }
            });
    },
    methods:    
	{
        validacija: function()  
		{
            let valid = true;

            if (this.korisnik.ime.length <= 1 || !/^[a-zA-Z]+$/.test(this.korisnik.ime))   
			{
                this.$refs.ime.classList.remove("is-valid");
                this.$refs.ime.classList.add("is-invalid");
                valid = false;
            } 
			else  
			{
                if (this.$refs.ime.classList.contains('is-invalid'))    
				{
                    this.$refs.ime.classList.remove("is-invalid");
                    this.$refs.ime.classList.add("is-valid");
                }
            }

            if (this.korisnik.prezime.length <= 1 || !/^[a-zA-Z]+$/.test(this.korisnik.prezime))   
			{
                this.$refs.prezime.classList.remove("is-valid");
                this.$refs.prezime.classList.add("is-invalid");
                valid = false;
            } 
			else  
			{
                if (this.$refs.prezime.classList.contains('is-invalid'))    
				{
                    this.$refs.prezime.classList.remove("is-invalid");
                    this.$refs.prezime.classList.add("is-valid");
                }
            }

            if (this.novasifra.length == 0 && this.novasifraponovo.length == 0)    
			{
                if (this.$refs.novasifra.classList.contains("is-invalid"))   
				{
                    this.$refs.novasifra.classList.remove("is-invalid");
                }

                if (this.$refs.novasifraponovo.classList.contains("is-invalid"))  
				{
                    this.$refs.novasifraponovo.classList.remove("is-invalid");
                }
            } 
			else 
			{
                if (this.starasifra == this.korisnik.sifra)    
				{
                    if (this.$refs.starasifra.classList.contains("is-invalid"))   
					{
                        this.$refs.starasifra.classList.remove("is-invalid");
                        this.$refs.starasifra.classList.add("is-valid");
                    }

                    if (this.sifra != this.novasifraponovo)   
					{
                        this.$refs.novasifra.classList.remove("is-valid");
                        this.$refs.novasifra.classList.add("is-invalid");
                        this.$refs.novasifraponovo.classList.remove("is-valid");
                        this.$refs.novasifraponovo.classList.add("is-invalid");
                        valid = false;
                    } 
					else  
					{
                        if (this.$refs.novasifra.classList.contains("is-invalid"))   
						{
                            this.$refs.novasifra.classList.remove("is-invalid");
                            this.$refs.novasifra.classList.add("is-valid");
                        }

                        if (this.$refs.novasifraponovo.classList.contains("is-invalid"))  
						{
                            this.$refs.novasifraponovo.classList.remove("is-invalid");
                            this.$refs.novasifraponovo.classList.add("is-valid");
                        }

                        this.korisnik.sifra = this.sifra;
                    }
                } 
				else  
				{
                    this.$refs.starasifra.classList.remove("is-valid");
                    this.$refs.starasifra.classList.add("is-invalid");
                    valid = false;
                }
            }

            if (valid)  
			{
                this.izmeniKorisnika();
            }
        },

        izmeniKorisnika: function() 
		{
            axios
                .put('app/izmeniKorisnika', this.korisnik, 
				{
                    headers:    
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')
                    }
                })
                .then(response => 
				{
                    this.$refs.msg.classList.remove("error-msg");
                    this.$refs.msg.innerHTML = response.data.sadrzaj;
                })
                .catch(error => 
				{
                    console.log(error);
                    this.$refs.msg.classList.add("error-msg");
                    this.$refs.msg.innerHTML = error.response.data.sadrzaj;
                    if (error.response.status == 400 || error.response.status == 403)   
					{
                        window.localStorage.removeItem('jwt');
                        window.location = 'login.html';
                    }
                });
        }
    }
});