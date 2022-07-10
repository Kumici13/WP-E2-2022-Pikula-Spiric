new Vue({
    el: '#login-app',
    data:   {
        korisnickoIme: '',
        sifra: '',
        valid: true
    },
	mounted()   
	{
		window.localStorage.removeItem('uloga');
        window.localStorage.removeItem('jwt');
	},
    methods:    {
        provera: function()  
		{

            sveDobro = true;

            if (this.$refs.korisnickoIme.value.length == 0)   
			{
                this.$refs.korisnickoIme.classList.remove("is-valid");
                this.$refs.korisnickoIme.classList.add("is-invalid");
                sveDobro = false;
            } 
			else  
			{
                if (this.$refs.korisnickoIme.classList.contains('is-invalid'))    
				{
                    this.$refs.korisnickoIme.classList.remove("is-invalid");
                    this.$refs.korisnickoIme.classList.add("is-valid");
                }
            }

            if (this.$refs.sifra.value.length == 0)   
			{
                this.$refs.sifra.classList.remove("is-valid");
                this.$refs.sifra.classList.add("is-invalid");
                sveDobro = false;
            } 
			else  
			{
                if (this.$refs.korisnickoIme.classList.contains('is-invalid'))    
				{
                    this.$refs.korisnickoIme.classList.remove("is-invalid");
                    this.$refs.korisnickoIme.classList.add("is-valid");
                }
            }

            if (sveDobro)
            {
                this.login();
            }
        },

        login: function()   
		{
            var putanja = '/app/login';

            let payload = this.korisnickoIme + '&' + this.sifra;

            axios
                .post(putanja, payload)
                .then(response => 
				{
                    if (response.data.hasOwnProperty('JWTToken'))   
					{
						console.log(response.data.JWTToken)
                        window.localStorage.setItem('jwt', response.data.JWTToken);
						window.localStorage.setItem('uloga', response.data.uloga);
			
							
                        let uloga = response.data.uloga;
                        if (uloga == 0) 
						{
                            window.location = "kupacHome.html";
                        } 
						else if (uloga == 1) 
						{
                            window.location = "menadzerHome.html";
                        }
						else if (uloga == 2)  
						{
                            window.location = "administratorHome.html";
                        }
						else if(uloga == 3)
						{
							window.location = "trenerHome.html";
						}
						
                    } 
					else
					{
                        console.log(response);
                    }
                })
                .catch(error => 
				{
                    console.log(error);
                    this.$refs.err_msg.classList.add("is-invalid");
                    this.$refs.err_msg.innerHTML = "Ne postoji nalog sa ovim korsnickim imenom i sifrom";
                });
        }
    }
});
