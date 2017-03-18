<template>
    <div class="content">

        <md-card md-with-hover class="table">
         
            <md-card-content>
                <form >
                    <md-input-container md-inline class="addLink">
                        <label>http://example.com/article</label>
                        <md-input v-model="link"></md-input>
                    </md-input-container>
                    <md-button v-on:click="submit" class="md-raised md-primary">Submit</md-button>
                </form>
            </md-card-content>
        </md-card>

        <md-table-card  md-with-hover class="table">
            <md-toolbar>
               
                <md-button class="md-icon-button">
                    <md-icon>filter_list</md-icon>
                </md-button>

                <md-button class="md-icon-button">
                    <md-icon>search</md-icon>
                </md-button>
            </md-toolbar>

            <md-table md-sort="url" md-sort-type="desc" @select="onSelect" @sort="onSort">
                <md-table-header>
                    <md-table-row>
                        <md-table-head md-sort-by="url">Title</md-table-head>
                        <md-table-head md-sort-by="title" md-numeric md-tooltip="Is this searchable by other users">Visible</md-table-head>
                    </md-table-row>
                </md-table-header>

                <md-table-body>
                    <md-table-row v-for="(row, rowIndex) in links" :key="rowIndex" :md-item="row" md-auto-select md-selection>
                       <!-- <md-table-cell v-for="(column, columnIndex) in row" :key="columnIndex" :md-numeric="columnIndex !== 'url' && columnIndex !== 'comment'"
                            v-if="columnIndex !== 'type' && columnIndex !=='id'">
                            {{ column }}
                        </md-table-cell>
                  -->
                  <md-table-cell>
                  {{row.url}}
                  </md-table-cell>
                  <md-table-cell>
                   {{row.title}}
                  </md-table-cell>
                
                    </md-table-row>
                </md-table-body>
            </md-table>

            <md-table-pagination md-size="5" md-total="10" md-page="1" md-label="Rows" md-separator="of" :md-page-options="[5, 10, 25, 50]"
                @pagination="onPagination"></md-table-pagination>
        </md-table-card>
    </div>
</template>

<script>
import LinksService from '../services/LinksService'
    export default {
        name: 'Content',

        mounted: function() {
            this.getData()
        },
        data: function() {
            return {  
                link: '',
                links: [
                    {
                        url: 'Google.com',
                        title: 'true'
                    }
                ]
            }
        },
        methods: {
            submit: function () {
                console.log('submit' + this.link)
                LinksService.submit('lehel',this.link, () => {
                      this.link = ''
                      this.getData()   
                } ,
                () => console.log('submit failed'))
            },
             onSort: function () {
                console.log('onSort')
            },
             onSelect: function () {
                console.log('onSelect')
            },
             onPagination: function () {
                console.log('onPagination')
            },
            getData: function() {
            var self = this
            console.log('mounted')
            console.log(self.links)
             LinksService.getLinksForUser('lehel',(d) => { 
                 var newLinks = JSON.parse(d)
                 console.log(self.links)
                 console.log(newLinks)
                 self.links = newLinks 
                 },() => console.log('faill2')) 
            }

        }
    }

</script>

<style scoped>
  .table{
        margin: 40px;
  }

  .addLink{
        float:left;
        display:inline; 
        width: 75%;
  }
</style>