new Vue({
    el: '#clanarine-app',
    data:   
	{
		clanarine: [],
		//changeActivityOfClanarine: [],
        pretragaClanarine: '',
        tipTr: '',
        brTr: '',
        brUlazaka: '',
        cena: '',
      
      
    },
   
    methods:    
	{
        ulogovanKorisnik: function()    
		{
            return window.localStorage.getItem('jwt') != null;
        },
        
        setClassic: function()
		{
			 window.localStorage.setItem("clanarina", 'Classic');
			 window.location.href='odaberi.html';
		},
		
		setStudioClassic: function()
		{
			window.localStorage.setItem("clanarina", 'StudioClassic');
			window.location.href='odaberi.html';
		},
		
		setStudioElite: function()
		{
			window.localStorage.setItem("clanarina", 'StudioElite');
			window.location.href='odaberigodisnju.html';
		},
 
       	
    },
    computed:   
	{
        clanarineFilter: function() 
		{
            return this.clanarine.filter((objekat) => 
			{	
                return 	   ((objekat.datumPlacanja.toLowerCase().match(this.pretragaClanarine.toLowerCase()))
						 )
											
				});
        }
    }
	
});




