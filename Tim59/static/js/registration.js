new Vue({
    el: '#register-app',
    data: {
        ime: '',
        prezime: '',
        korisnickoIme: '',
        sifra: '',
        sifraPonovo: '',
        pol: -1,
        valid: true
    },
    methods: {
        provera: function()   
		{
            valid = true;

            // proveri ime
            if (this.$refs.ime.value.length <= 1 || !/^[a-zA-Z]+$/.test(this.$refs.ime.value))   
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

            if (this.$refs.prezime.value.length <= 1 || !/^[a-zA-Z]+$/.test(this.$refs.prezime.value))   
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

            if (this.$refs.korisnickoIme.value.length <= 1 || /\W/.test(this.$refs.korisnickoIme.value))   
			{
                this.$refs.korisnickoIme.classList.remove("is-valid");
                this.$refs.korisnickoIme.classList.add("is-invalid");
                valid = false;
            } 
			else  
			{
                if (this.$refs.korisnickoIme.classList.contains('is-invalid'))    
				{
                    this.$refs.korisnickoIme.classList.remove("is-invalid");
                    this.$refs.korisnickoIme.classList.add("is-valid");
                }
            }

            if (this.$refs.sifra.value.length < 8 || !this.proveriSifre() || !/^[0-9a-zA-Z]+$/.test(this.$refs.sifra.value))   
			{
                this.$refs.sifra.classList.remove("is-valid");
                this.$refs.sifra.classList.add("is-invalid");
                this.$refs.sifraPonovo.classList.remove("is-valid");
                this.$refs.sifraPonovo.classList.add("is-invalid");
                valid = false;
            } 
			else  
			{
                if (this.$refs.sifra.classList.contains('is-invalid'))    
				{
                    this.$refs.sifra.classList.remove("is-invalid");
                    this.$refs.sifra.classList.add("is-valid");
                }

                if (this.$refs.sifraPonovo.classList.contains('is-invalid'))    
				{
                    this.$refs.sifraPonovo.classList.remove("is-invalid");
                    this.$refs.sifraPonovo.classList.add("is-valid");
                }
            }

            if (this.$refs.pol.value == "") 
			{
                this.$refs.pol.classList.remove("is-valid");
                this.$refs.pol.classList.add("is-invalid");
                valid = false;
            } 
			else 
			{
                if (this.$refs.pol.classList.contains('is-invalid'))    
				{
                    this.$refs.pol.classList.remove("is-invalid");
                    this.$refs.pol.classList.add("is-valid");
                }
            }

            if (valid)  
			{
                this.registruj();
            }
        },

        proveriSifre: function()  
		{
            if (this.$refs.sifraPonovo.value.length != this.$refs.sifra.value.length)  
			{
                return false;
            }

            for (let i = 0; i < this.$refs.sifraPonovo.value.length; i++)  
			{
                if (this.$refs.sifraPonovo.value[i] != this.$refs.sifra.value[i])   
				{
                    return false;
                }
            }

            return true;
        },

        registruj: function()   
		{
        	var korisnik = 
			{
                'ime': this.ime,
                'prezime': this.prezime,
                'korisnickoIme': this.korisnickoIme,
                'sifra': this.sifra,
                'pol': this.pol
            };
			
            if (!this.ulogovanKorisnik()) 
			{
                let putanja = '/app/registracija/kupac';

                axios
                    .post(putanja, korisnik, 
					{
                        headers: 
						{
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(response => 
					{
                        if (response.data.hasOwnProperty('korisnickoIme'))  
						{
                            window.localStorage.setItem('jwt', response.data.JWTToken);
                            window.location = "kupacHome.html";
                        } 
						else  
						{
                            console.log(response);
                        }
                    })
                    .catch(error => 
					{
                        console.log(error);
                    });
            }
			else
			{
            	console.log("Neko je vec ulogovan");
				window.localStorage.removeItem('jwt');
				this.provera();
			}
			
        },

        ulogovanKorisnik: function() 
		{
            return window.localStorage.getItem('jwt') != null;
        }
    }
});
