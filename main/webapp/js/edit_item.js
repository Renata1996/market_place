var app = new Vue({
el: '#form',
data: {

messageTitle: '',
messageDescription: '',
messagePrice: '',
messageTime: '',
messageChecked: '',
messageIncrement: '',
messageError: '',


title: '',
description: '',
startPrice: '',
time: '',
checked: '',
increment: '',

},
methods: {
validateTitle: function(){
if(this.title== ''){
        this.messageError = 'Field should not be empty!';
    } else{
        this.messageError = '';
    }
},
validatePrice: function(){
 var price = parseInt(this.startPrice,10);

    if(isNaN(price) || price < 0){
        this.messagePrice='must be a number and > 0';
    } else{
        this.messagePrice='';
    }
},
validateChecked: function(){
    if(!this.checked){
        var bid = parseInt(this.increment);
        if(isNaN(bid) || bid < 0){
        this.messageIncrement='must be a number and > 0';
        } else{
        this.messageIncrement='';
        }
    }else{
        this.messageIncrement='';
    }
     if(!this.checked){
            var date = this.time.split(':');
            var minutes = parseInt(date[1]);
            var hours = parseInt(date[0]);
            if(isNaN(minutes) || isNaN(hours) || minutes >= 60 || minutes <= 0 || hours <= 0) {
                this.messageTime = 'format HH:MM and 0 <= MM <= 60 and HH >= 0'
            }else{
                this.messageTime = '';
            }
        }
        else{
             this.messageTime='';
        }
},
/*validateForm: function () {
    if(this.title== ''){
        this.messageError = 'Field should not be empty!';
    } else{
        this.messageError = '';
    }
    var price = parseInt(this.startPrice,10);

    if(isNaN(price) || price < 0){
        this.messagePrice='must be a number and > 0';
    } else{
        this.messagePrice='';
    }

    if(!this.checked){
        var bid = parseInt(this.increment);
        if(isNaN(bid) || bid < 0){
        this.messageIncrement='must be a number and > 0';
        } else{
        this.messageIncrement='';
        }
    }else{
        this.messageIncrement='';
    }
    if(!this.checked){
        var date = this.time.split(':');
        var minutes = parseInt(date[1]);
        var hours = parseInt(date[0]);
        if(isNaN(minutes) || isNaN(hours) || minutes >= 60 || minutes <= 0 || hours <= 0) {
            this.messageTime = 'format HH:MM and 0 <= MM <= 60 and HH >= 0'
        }else{
            this.messageTime = '';
        }
    }
    else{
         this.messageTime='';
    }

},*/
agreeForm: function(f) {
    if (!f.checkbox.checked){
        f.increment.disabled = false;
        f.time.disabled = false;
    }
    else{
     f.increment.disabled = true;
     f.time.disabled = true;
     this.time='';
     this.increment='';
    }
   },
reset: function(){
    this.messageTitle= '';
    this.messageDescription= '';
    this.messagePrice= '';
    this.messageTime= '';
    this.messageChecked= '';
    this.messageIncrement= '';
    this.messageError= '';

    this.title= '';
    this.description= '';
    this.startPrice= '';
    this.time= '';
    this.checked= '';
    this.increment= '';
}
}
})