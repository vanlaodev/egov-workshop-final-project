<template>
   <div >   
        <div >  
            <input type="text" v-model="filter_name" placeholder="Filter titles by title name" class="form-control" >
            
            <table class="table table-condensed ">
                <tr class="titlestyle">
                    <td>#</td>
                    <td><a href="#" @click.prevent="sort('name')" :class="{'active': sortBy == 'name'}" >TITLE </a></td>
                    <td><a href="#" @click.prevent="sort('from')" :class="{'active': sortBy == 'from'}" >FROM</a></td>
                    <td><a href="#" @click.prevent="sort('to')" :class="{'active': sortBy == 'to'}" >TO</a></td>
                    <td></td>
                </tr>
                <tr v-bind:key="r.id" v-for="(r, index) in filteredRows.slice(pageStart, pageStart + countOfPage)"  :class="[{'darker':index%2!=1},{'light':index%2!=0}]" > 
                    <td>{{ (currPage-1) * countOfPage + index + 1 }}</td>
                    <td>{{ r.name }}</td>
                    <td>{{ r.from }}</td>
                    <td>{{ r.to }}</td>
                    <td>
                        <a href="#" class="editcolor a-btn-slide-text" @click.prevent="editObject(index)">
                            <!-- <span style="font-size: 18px"  class="glyphicon glyphicon-edit" aria-hidden="true"></span> -->
                            <b-icon-x-circle></b-icon-x-circle>
                        </a>
                        <a href="#" class="deletecolor a-btn-slide-text" @click.prevent="deleteObject(index)">
                            <!-- <span style="font-size: 18px" class="glyphicon glyphicon-remove " aria-hidden="true"></span> -->
                            <b-icon-x-circle></b-icon-x-circle>
                        </a>
                    </td>
                </tr>
            </table> 
        
            <div class="pagination">
            <ul>
                <li v-bind:class="{'disabled': (currPage === 1)}" 
                    @click.prevent="setPage(currPage-1)"><a href="#">Prev</a></li>
                <li v-bind:key="n" v-for="n in totalPage"
                    v-bind:class="{'active': (currPage === (n))}" 
                    @click.prevent="setPage(n)"><a href="#">{{n}}</a></li>
                <li v-bind:class="{'disabled': (currPage === totalPage)}" 
                    @click.prevent="setPage(currPage+1)"><a href="#">Next</a></li>
            </ul>
            </div>
        </div>
    </div>
</template>


<style>
 @import url("https://unpkg.com/element-ui@2.11.1/lib/theme-chalk/index.css"); 
    body {
      margin: 20px;
      max-width: 800px;
      padding: 0 20px;
      color: rgb(8, 8, 8);
      background: #C5DDEB;
      font: 14px/20px "Lato", Arial, sans-serif;
    }
    
    
    .darker {
      
      background-color: rgb(163, 162, 162);
    }

    .light {
      
        background-color: #dfdfdf;
    }

    .titlestyle{
        background-color: #222222;
        color:rgb(163, 162, 162);
    }

        
    .border {
      border: 2px solid #7e7c7c;

    }
    .deletecolor{
        color: rgb(184, 11, 11);
        
    }
    .deletecolor:hover,
    .deletecolor:active,
    .deletecolor:focus
    {
        color: rgb(184, 11, 11);
        
    }
    .editcolor{
        color: rgba(3, 94, 11, 0.76);
        
    }
    .editcolor:hover,
    .editcolor:active,
    .editcolor:focus
    {
        color: rgba(3, 94, 11, 0.76);
        
    }
    a {
    font-weight: normal;
    color: rgb(163, 162, 162);
    }

    a.active {
    font-weight: bold;
    color: rgb(233, 89, 6);
    }
    .pagination{
    margin:20px 0;
    }
    .pagination ul{
    display:inline-block;
    *display:inline;
    *zoom:1;
    margin-left:0;
    margin-bottom:0;
    -webkit-border-radius:4px;
    -moz-border-radius:4px;
    border-radius:4px;
    -webkit-box-shadow:0 1px 2px rgba(0, 0, 0, 0.05);
    -moz-box-shadow:0 1px 2px rgba(0, 0, 0, 0.05);
    box-shadow:0 1px 2px rgba(0, 0, 0, 0.05);}
    .pagination ul>li{display:inline;}
    .pagination ul>li>a,.pagination ul>li>span{
    float:left;padding:4px 12px;line-height:20px;text-decoration:none;background-color:#ffffff;border:1px solid #dddddd;border-left-width:0;}
    .pagination ul>li>a:hover,.pagination ul>li>a:focus,.pagination ul>.active>a,.pagination ul>.active>span{background-color:#f5f5f5;}
    .pagination ul>.active>a,.pagination ul>.active>span{color:#999999;cursor:default;}
    .pagination ul>.disabled>span,.pagination ul>.disabled>a,.pagination ul>.disabled>a:hover,.pagination ul>.disabled>a:focus{
    color:#999999;background-color:transparent;cursor:default;}
    .pagination ul>li:first-child>a,.pagination ul>li:first-child>span{
    border-left-width:1px;
    -webkit-border-top-left-radius:4px;
    -moz-border-radius-topleft:4px;
    border-top-left-radius:4px;
    -webkit-border-bottom-left-radius:4px;
    -moz-border-radius-bottomleft:4px;border-bottom-left-radius:4px;}
    .pagination ul>li:last-child>a,.pagination ul>li:last-child>span{
    -webkit-border-top-right-radius:4px;
    -moz-border-radius-topright:4px;
    border-top-right-radius:4px;
    -webkit-border-bottom-right-radius:4px;
    -moz-border-radius-bottomright:4px;
    border-bottom-right-radius:4px;}


    ul,ol{padding:0;margin:0 0 10px 25px;}
    ul ul,ul ol,ol ol,ol ul{margin-bottom:0;}
    li{line-height:20px;}
    ul.unstyled,ol.unstyled{margin-left:0;list-style:none;}
    ul.inline,ol.inline{margin-left:0;list-style:none;}ul.inline>li,ol.inline>li{display:inline-block;*display:inline;*zoom:1;padding-left:5px;padding-right:5px;}

    .table-condensed th,.table-condensed td{padding:8px 9px;}

</style>

<script>


import { BIconXCircle } from 'bootstrap-vue'

export default {
    name: 'Inquiry',
    components: {
        BIconXCircle,
    },
    data() {
        return { 
            rows: [
                { id: 1, name: '關於2021年DOI的盤點項目', from: '01/02/2021', to:'28/02/2021' },
                { id: 2, name: '關於2021年DAF的盤點項目', from: '05/02/2021', to:'28/02/2021' },
                { id: 3, name: '關於2021年ABC的盤點項目', from: '05/02/2021', to:'28/02/2021' },
                { id: 4, name: '關於2021年CDE的盤點項目', from: '05/02/2021', to:'28/02/2021' },
                { id: 5, name: '關於2021年FGH的盤點項目', from: '01/02/2021', to:'27/02/2021' },
                { id: 6, name: '關於2021年IJK的盤點項目', from: '06/02/2021', to:'27/02/2021' },
                { id: 7, name: '關於2021年LMN的盤點項目', from: '03/02/2021', to:'26/02/2021' },
                { id: 8, name: '關於2021年OPQ的盤點項目', from: '01/02/2021', to:'28/02/2021' },
                { id: 9, name: '關於2021年RST的盤點項目', from: '01/02/2021', to:'28/02/2021' }
            ],
            countOfPage: 5,
            currPage: 1,
            sortBy: 'name',
            sortDirection: 'asc',
            filter_name: '' 
        }
    },
    computed: {
        filteredRows: function(){
            return this.rows;
            // var filter_name = this.filter_name.toLowerCase();

            // this.rows.sort((p1,p2) => {
            //     let modifier = 1;
            //     if(this.sortDirection === 'desc') modifier = -1;
            //     if(p1[this.sortBy] < p2[this.sortBy]) return -1 * modifier; if(p1[this.sortBy] > p2[this.sortBy]) return 1 * modifier;
            //     return 0;
            // });

            // if (this.filter_name.trim() !== '') {this.currPage=1;} 

            // return (this.filter_name.trim() !== '' ) ?  this.rows.filter(function(d){ return d.name.toLowerCase().indexOf(filter_name) > -1;  }) : this.rows;
        },
        pageStart: function(){
            return (this.currPage - 1) * this.countOfPage;
        },
        totalPage: function(){
            return Math.ceil(this.filteredRows.length / this.countOfPage);
        }
    },
    methods: {
        setPage: function(idx){
        if( idx <= 0 || idx > this.totalPage ){
            return;
        }
        this.currPage = idx;
        },
        sort: function(s){
            if(s === this.sortBy) {
                this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
            }
            this.sortBy = s;
        },
        deleteObject: function() {
        // deleteObject: function(index) {
            // JSAlert.confirm("Do you really want to delete?").then(result => {
            //     if (result) {
            //         for (let z=0; z<this.rows.length;z++)
            //         if (this.rows[z].name == this.filteredRows[(this.currPage-1) * this.countOfPage + index ].name) { 
            //         this.$delete(this.rows, z );
            //         break; }
            //         }  
            // })
        },
        editObject: function(index) {
        localStorage.setItem("keytitle",this.filteredRows[(this.currPage-1) * this.countOfPage + index ].name);
        localStorage.setItem("keyfrom",this.filteredRows[(this.currPage-1) * this.countOfPage + index ].from);
        this.$router.replace('modifyticket');
        },
      },
    props: {
    }
}
</script>

