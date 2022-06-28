new Vue({
    el: '#regkorisnici-app',
    data:   
	{
		regKorisnici: [],
     	pretragaIme: '',
        pretragaPrezime: '',
        pretragaKorisnickoIme: '',
        filtriranje: ''
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
       	
    },
    computed:   
	{
        regkorisniciFilter: function() 
		{
            return this.regKorisnici.filter((objekat) => 
			{	
                return 	   ((objekat.ime.toLowerCase().match(this.pretragaIme.toLowerCase()))
						&& (objekat.prezime.toLowerCase().match(this.pretragaPrezime.toLowerCase()))
						&& (objekat.korisnickoIme.toLowerCase().match(this.pretragaKorisnickoIme.toLowerCase())) )
											
				});
        }
    }
	
});




