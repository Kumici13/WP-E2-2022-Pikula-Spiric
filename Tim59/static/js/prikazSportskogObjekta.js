new Vue({
    el: '#prikaz-sportskog-objekta-app',
    data:   
	{
        sportskiObjekat:'',
		getprikazSportskiObjekat:''
    },
    
    
     mounted()  
	{
		this.uloga = window.localStorage.getItem('uloga');

        if (this.uloga == null || this.uloga != 0) 
		{
            alert('Sta se pravis pametan!');
            window.localStorage.removeItem('uloga');
            window.localStorage.removeItem('jwt');
			window.location = "index.html";
        }

		let jwt = window.localStorage.getItem('idSporskogObjekta');
        axios
            .post('app/getSportskiObjekatWithID', jwt,
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt'),
						'idSporskogObjekta':window.localStorage.getItem('idSporskogObjekta')
                    }
                })
            .then(response => 
			{
                this.sportskiObjekat = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                
            });
    },
    
     methods:    
	{
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
    }
});




