new Vue({
    el: '#sportski-objekti-app',
    data:   
	{
        sportskiObjekti: [],
        pretragaNaziv: '',
        pretragaPoTipu: '5',
        pretragaLokacija: '',
        pretragaProsecnaOcena: '6'
    },
    mounted()  
	{
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
        prikaziAdresu: function(objekat)   {
            return objekat.lokacija.adresa['ulica'] + " " + objekat.lokacija.adresa['broj'] + "\n" + objekat.lokacija.adresa['mesto'];
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
                return 	(objekat.lokacija.adresa.mesto.toLowerCase().match(this.pretragaLokacija.toLowerCase()) || objekat.lokacija.adresa.ulica.toLowerCase().match(this.pretragaLokacija.toLowerCase()))
						&& (objekat.tipObjekta == this.pretragaPoTipu || this.pretragaPoTipu=='5')
						&& (objekat.naziv.toLowerCase().match(this.pretragaNaziv.toLowerCase()))
						&& (objekat.prosecnaOcena ==(this.pretragaProsecnaOcena) || this.pretragaProsecnaOcena == 6) 					
				});
        }
    }
	
});




