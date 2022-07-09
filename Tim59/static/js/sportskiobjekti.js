new Vue({
    el: '#sportski-objekti-app',
    data:   
	{
        uloga: '',
        sportskiObjekti: [],
        pretragaNaziv: '',
        pretragaPoTipu: '5',
        pretragaLokacija: '',
        pretragaProsecnaOcena: '6'
    },
    mounted()  
	{
        this.uloga = window.localStorage.getItem('uloga');

        if (this.uloga == null) 
		{
            window.localStorage.removeItem('uloga');
            window.localStorage.removeItem('jwt');
        }

        axios
            .get('app/getSportskiObjekti')
            .then(response => 
			{
                this.sportskiObjekti = response.data;
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
		prikaziRadnoVreme: function(objekat)   
		{
					let s = ""; 
					
					if(objekat.radnoVreme.ponedeljakDan['radniDan']== false)
					{
						s += objekat.radnoVreme.ponedeljakDan['imeDana'] + ": Ne radimo \n";
					}
					else
					{
						s += objekat.radnoVreme.ponedeljakDan['imeDana'] + " " + objekat.radnoVreme.ponedeljakDan['danStart'] + " - " + objekat.radnoVreme.ponedeljakDan['danEnd'] + " \n"
					}
					  
					if(objekat.radnoVreme.utorakDan['radniDan']== false)
					{
						s += objekat.radnoVreme.utorakDan['imeDana'] + ": Ne radimo \n";
					}
					else
					{
						s += objekat.radnoVreme.utorakDan['imeDana'] + " " + objekat.radnoVreme.utorakDan['danStart'] + " - " + objekat.radnoVreme.utorakDan['danEnd'] + " \n"
					}
					
					if(objekat.radnoVreme.sredaDan['radniDan']== false)
					{
						s += objekat.radnoVreme.sredaDan['imeDana'] + ": Ne radimo \n";
					}
					else
					{
						s += objekat.radnoVreme.sredaDan['imeDana'] + " " + objekat.radnoVreme.sredaDan['danStart'] + " - " + objekat.radnoVreme.sredaDan['danEnd'] + " \n"
					}
					
					if(objekat.radnoVreme.cetvrtakDan['radniDan']== false)
					{
						s += objekat.radnoVreme.cetvrtakDan['imeDana'] + ": Ne radimo \n";
					}
					else
					{
						s += objekat.radnoVreme.cetvrtakDan['imeDana'] + " " + objekat.radnoVreme.cetvrtakDan['danStart'] + " - " + objekat.radnoVreme.cetvrtakDan['danEnd'] + " \n"
					}
					
					if(objekat.radnoVreme.petakDan['radniDan']== false)
					{
						s += objekat.radnoVreme.petakDan['imeDana'] + ": Ne radimo \n";
					}
					else
					{
						s += objekat.radnoVreme.petakDan['imeDana'] + " " + objekat.radnoVreme.petakDan['danStart'] + " - " + objekat.radnoVreme.petakDan['danEnd'] + " \n"
					}
					
					if(objekat.radnoVreme.subotaDan['radniDan']== false)
					{
						s += objekat.radnoVreme.subotaDan['imeDana'] + ": Ne radimo \n";
					}
					else
					{
						s += objekat.radnoVreme.subotaDan['imeDana'] + " " + objekat.radnoVreme.subotaDan['danStart'] + " - " + objekat.radnoVreme.subotaDan['danEnd'] + " \n"
					}
					
					if(objekat.radnoVreme.nedeljaDan['radniDan']== false)
					{
						s += objekat.radnoVreme.nedeljaDan['imeDana'] + ": Ne radimo \n";
					}
					else
					{
						s += objekat.radnoVreme.nedeljaDan['imeDana'] + " " + objekat.radnoVreme.nedeljaDan['danStart'] + " - " + objekat.radnoVreme.nedeljaDan['danEnd'] + " \n"
					}
					return s;
        },
        prikaziAdresu: function(objekat)   {
            return objekat.lokacija.adresa['ulica'] + " " + objekat.lokacija.adresa['broj'] + " \n" + objekat.lokacija.adresa['mesto']+ " " +objekat.lokacija.adresa['postanskiBroj']+ "\n"  + objekat.lokacija.geoDuzina + " " +  objekat.lokacija.geoSirina;
        },
		prikaziSliku: function(slika)
		{
            if (slika != null)  
			{
                return "data:image/jpeg;base64," + slika;
            } 
			else  
			{
                return '';
            }
        }
    },
    computed:   
	{
        filtriraniObjekti: function() 
		{
            return this.sportskiObjekti.filter((objekat) => 
			{	
                return 	   ((objekat.lokacija.adresa.broj.toString().match(this.pretragaLokacija.toLowerCase())) 
                		|| (objekat.lokacija.geoSirina.toString().match(this.pretragaLokacija.toLowerCase())) 
                		|| (objekat.lokacija.geoDuzina.toString().match(this.pretragaLokacija.toLowerCase())) 
                		|| (objekat.lokacija.adresa.postanskiBroj.toString().match(this.pretragaLokacija.toLowerCase()))
                		|| (objekat.lokacija.adresa.mesto.toLowerCase().match(this.pretragaLokacija.toLowerCase())) 
                		|| (objekat.lokacija.adresa.ulica.toLowerCase().match(this.pretragaLokacija.toLowerCase())))
						&& (objekat.tipObjekta == this.pretragaPoTipu || this.pretragaPoTipu=='5')
						&& (objekat.naziv.toLowerCase().match(this.pretragaNaziv.toLowerCase()))
						&& (objekat.prosecnaOcena ==(this.pretragaProsecnaOcena) || this.pretragaProsecnaOcena == 6) 					
				});
        }
    }
	
});




