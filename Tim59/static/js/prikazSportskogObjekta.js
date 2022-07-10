new Vue({
    el: '#prikaz-sportskog-objekta-app',
    data:   
	{
        sportskiObjekat:'',
		sadrzaji:[],
		uloga:'',
		date:'',
		brojac: 0
    },
    
    
     mounted()  
	{
		this.uloga = window.localStorage.getItem('uloga');
		
		var d = new Date();
        this.date = d.toLocaleDateString("en-GB");  

        if (this.uloga == null) 
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

			axios
            .post('app/getSadrzajiZaSportskiObjekat', jwt,
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
                this.sadrzaji = response.data;
			})
            .catch(error => 
			{
                console.log(error);
                
            });
    },    
     methods:    
	{
		sledeciDan: function()
		{
			this.brojac++;
		    var d = new Date();
			d.setDate(d.getDate() + this.brojac);
            this.date = d.toLocaleDateString("en-GB");   
			
		},
		prethodniDan: function()
		{
			this.brojac--;	
			var d = new Date();
			d.setDate(d.getDate() + this.brojac);
            this.date = d.toLocaleDateString("en-GB"); 
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
        },

		zakaziTrening: function(treningId)
		{
			let jwt = window.localStorage.getItem('idSporskogObjekta');
			axios
            .post('app/prijaviSeNaTrening', jwt,
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt'),
						'treningId': treningId,
						'dateTime': this.date
                    }
                })
            .then(response => 
			{
            	alert(response.data);
			})
            .catch(error => 
			{
                console.log(error);
            	alert(error.data);
                
            });
        },

		prikaziStart: function(objekat)
		{	
			var d = new Date();
			d.setDate(d.getDate() + this.brojac);   
			let day = d.getDay();
				if(day == 0)
				{
					return objekat.radnoVreme.nedeljaDan.danStart;
				}
				else if(day == 1)
				{
					return objekat.radnoVreme.ponedeljakDan.danStart;
				}
				else if(day == 2)
				{
					return objekat.radnoVreme.utorakDan.danStart;
				}
				else if(day == 3)
				{
					return objekat.radnoVreme.sredaDan.danStart;
				}
				else if(day == 4)
				{
					return objekat.radnoVreme.cetvrtakDan.danStart;
				}
				else if(day == 5)
				{
					return objekat.radnoVreme.petakDan.danStart;
				}
				else if(day == 6)
				{
					return objekat.radnoVreme.subotaDan.danStart;
				}		
		},
		
		prikaziKraj: function(objekat)
		{
			var d = new Date();
			d.setDate(d.getDate() + this.brojac);   
			let day = d.getDay();
				if(day == 0)
				{
					return objekat.radnoVreme.nedeljaDan.danEnd;
				}
				else if(day == 1)
				{
					return objekat.radnoVreme.ponedeljakDan.danEnd;
				}
				else if(day == 2)
				{
					return objekat.radnoVreme.utorakDan.danEnd;
				}
				else if(day == 3)
				{
					return objekat.radnoVreme.sredaDan.danEnd;
				}
				else if(day == 4)
				{
					return objekat.radnoVreme.cetvrtakDan.danEnd;
				}
				else if(day == 5)
				{
					return objekat.radnoVreme.petakDan.danEnd;
				}
				else if(day == 6)
				{
					return objekat.radnoVreme.subotaDan.danEnd;
				}
		},
		
		filterPrazan: function()
		{
			var i;
			for (i in this.filterSadrzaji) 
			{
				
			}
			
			if(i == null)
			{
				return true;
			}
			
			return false;	
		}
    },
	computed:   
	{
        filterSadrzaji: function() 
		{
		    var d = new Date();
			d.setDate(d.getDate() + this.brojac);   
			let day = d.getDay();
            return this.sadrzaji.filter((objekat) => 
			{	
				if(day == 0)
				{
					return objekat.radnoVreme.nedeljaDan.radniDan;
				}
				else if(day == 1)
				{
					return objekat.radnoVreme.ponedeljakDan.radniDan;
				}
				else if(day == 2)
				{
					return objekat.radnoVreme.utorakDan.radniDan;
				}
				else if(day == 3)
				{
					return objekat.radnoVreme.sredaDan.radniDan;
				}
				else if(day == 4)
				{
					return objekat.radnoVreme.cetvrtakDan.radniDan;
				}
				else if(day == 5)
				{
					return objekat.radnoVreme.petakDan.radniDan;
				}
				else if(day == 6)
				{
					return objekat.radnoVreme.subotaDan.radniDan;
				}
			
                				
			});
        }
    }

});




