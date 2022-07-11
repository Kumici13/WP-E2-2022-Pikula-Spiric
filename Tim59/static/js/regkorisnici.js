new Vue({
    el: '#regkorisnici-app',
    data:   
	{
		regKorisnici: [],
		changeActivityOfUser: [],
     	pretragaIme: '',
        pretragaPrezime: '',
        pretragaKorisnickoIme: '',
        filtriranje: 'svi',
        sortiranje: undefined,
      
    },
    mounted()  
	{
        axios
            .get('app/getregKorisnici')
            .then(response => 
			{
                this.regKorisnici = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                alert(error.response.data.sadrzaj);
            });
    },
    methods:    
	{
        ulogovanKorisnik: function()    
		{
            return window.localStorage.getItem('jwt') != null;
        },
        
         prikaziUlogu: function(objekat)   
         {
            if(objekat == "0")
            {
				return "Kupac";
			}
            else if(objekat == "1")
            {
				return "Menadzer";
			}
            else if(objekat == "2")
            {
				return "Admin";
			}
            else
            {
				return "Trener";
			}
        },
        
         prikaziPol: function(objekat)
        {
			if(objekat == "0")
			{
				return "Zenski";
			}
			else
			{
				return "Muski"
			}
	
		},
        
        prikaziAktivnost: function(objekat)
        {
			if(objekat)
			{
				return "Aktivan";
			}
			else
			{
				return "Nije aktivan"
			}
	
		},
        
       changeActivity: function(regKorisnik)
       {
		this.korisnickoIme =  regKorisnik 
		
		axios
            .post('app/changeActivityOfUser' , this.korisnickoIme )
            .then(response => 
			{
                this.changeActivityOfUser = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                
            });
	
	   },
	    rastuceIme: function (a,b)
        {
			 if ( a.ime < b.ime )  {
              return -1;
            }
            if ( a.ime > b.ime )  {
              return 1;
            }
            return 0;
       
		},
		
		 opadajuceIme: function (a,b)
        {
			if ( a.ime > b.ime )  {
                return -1;
              }
              if ( a.ime < b.ime )  {
                return 1;
              }
              return 0;
       
		},
      
        
        sortirajPoImenu: function(a,b)
        {
			
	 		if (this.sortiranje == 'rastuceIme')   {
                this.regKorisnici.sort(this.rastuceIme);
            } else  {
                this.regKorisnici.sort(this.opadajuceIme);
            }
	
		},
		
		 rastucePrezime: function (a,b)
        {
			 if ( a.prezime < b.prezime )  {
              return -1;
            }
            if ( a.prezime > b.prezime )  {
              return 1;
            }
            return 0;
       
		},
		
		 opadajucePrezime: function (a,b)
        {
			if ( a.prezime > b.prezime )  {
                return -1;
              }
              if ( a.prezime < b.prezime )  {
                return 1;
              }
              return 0;
       
		},
      
        
        sortirajPoPrezimenu: function(a,b)
        {
			
	 		if (this.sortiranje == 'rastucePrezime')   {
                this.regKorisnici.sort(this.rastucePrezime);
            } else  {
                this.regKorisnici.sort(this.opadajucePrezime);
            }
	
		},
		
		 rastuceKorIme: function (a,b)
        {
			 if ( a.korisnickoIme < b.korisnickoIme )  {
              return -1;
            }
            if ( a.korisnickoIme > b.korisnickoIme )  {
              return 1;
            }
            return 0;
       
		},
		
		 opadajuceKorIme: function (a,b)
        {
			if ( a.korisnickoIme > b.korisnickoIme )  {
                return -1;
              }
              if ( a.korisnickoIme < b.korisnickoIme )  {
                return 1;
              }
              return 0;
       
		},
      
        
        sortirajPoKorImenu: function(a,b)
        {
			
	 		if (this.sortiranje == 'rastuceKorIme')   {
                this.regKorisnici.sort(this.rastuceKorIme);
            } else  {
                this.regKorisnici.sort(this.opadajuceKorIme);
            }
	
		},
		
		 rastuce: function (a, b) {
            if ( a.bodovi < b.bodovi )  {
              return -1;
            }
            if ( a.bodovi > b.bodovi )  {
              return 1;
            }
            return 0;
        },

        opadajuce: function(a, b)   {
            if ( a.bodovi > b.bodovi )  {
                return -1;
              }
              if ( a.bodovi < b.bodovi )  {
                return 1;
              }
              return 0;
        },

        sortirajPoBodovima: function(event)  {
            if (this.sortiranje == 'rastuce')   {
                this.regKorisnici.sort(this.rastuce);
            } else  {
                this.regKorisnici.sort(this.opadajuce);
            }
        },
        
      
        
       	filtrirajPoImenu: function()
        {
			if(this.filtriranje == "kupac")
			{
				
			}
		},
       	
    },
    computed:   
	{
        regkorisniciFilter: function() 
		{
            return this.regKorisnici.filter((objekat) => 
			{	
				
                return 	   ((objekat.ime.toLowerCase().match(this.pretragaIme.toLowerCase()))
						&& (objekat.prezime.toLowerCase().match(this.pretragaPrezime.toLowerCase()))
						&& (objekat.korisnickoIme.toLowerCase().match(this.pretragaKorisnickoIme.toLowerCase()))
						&& (objekat.uloga == this.filtriranje || this.filtriranje=='svi'))
											
				});
        }
    }
	
});




