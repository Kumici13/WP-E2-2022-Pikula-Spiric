new Vue({
    el: '#sadrzaj-app',
    data:   
	{
        getSadrzaji: '',
        getTreneri: [],
        pretragaCena: '',
		treningId: ''
        
    },
    
    
     mounted()  
	{
		this.uloga = window.localStorage.getItem('uloga');

        if (this.uloga == null || this.uloga != 1) 
		{
            alert('Sta se pravis pametan!');
            window.localStorage.removeItem('uloga');
            window.localStorage.removeItem('jwt');
			window.location = "index.html";
        }

		let jwt = window.localStorage.getItem('jwt');
        axios
            .post('app/gettrenerizaobjekat', jwt,
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')
                    }
                })
            .then(response => 
			{
                this.getTreneri = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                
            });
            
        axios
            .post('app/getTreningeZaObjekat', jwt,
				{
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt')
                    }
                })
            .then(response => 
			{
                this.getSadrzaji = response.data;
            })
            .catch(error => 
			{
                console.log(error);
                alert(error.response.data.sadrzaj);
            });
    },
    
     methods:    
	{
        izmeniSadrzajTreninga: function(trening)
       {
		
	   		this.trening = trening 
			console.log(trening);
	
	   },
	
	   obrisiTrening: function(trening)
       {
		let jwt = window.localStorage.getItem('jwt');
			console.log(trening);
			axios
            .post('app/obrisiTreningById', jwt,
				{
					
                    headers: 
					{
                        'Content-Type': 'application/json',
                        'Autorizacija': window.localStorage.getItem('jwt'),
						
                        'TreningId': trening,
                    }
                })
            .then(response => 
			{
                location.reload();
 				alert(response.data);
            })
            .catch(error => 
			{
                console.log(error);
                alert(error.response.data.sadrzaj);
            });
	
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
		prikaziRadnoVreme: function(objekat)   
		{
					console.log(objekat);
					let s = ""; 
					
					if(objekat.radnoVreme.ponedeljakDan['radniDan']== false)
					{
						s += objekat.radnoVreme.ponedeljakDan['imeDana'] + ": Ne radimo \n";
					}
					else
					{
						s += objekat.radnoVreme.ponedeljakDan['imeDana'] + " " + objekat.radnoVreme.ponedeljakDan['danStart'] + " - " + objekat.radnoVreme.ponedeljakDan['danEnd'] + '\n'
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
        }
    }
});




