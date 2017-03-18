<template>
    <div class="content">

        <md-card md-with-hover>
            <md-card-header>
                <div class="md-title">Add link</div>
            </md-card-header>
            <md-card-content>
                <form>
                    <md-input-container md-inline>
                        <label>http://example.com/article</label>
                        <md-input v-model="link"></md-input>
                    </md-input-container>
                    <md-button v-on:click="submit" class="md-raised md-primary">Login</md-button>
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

            <md-table md-sort="dessert" md-sort-type="desc" @select="onSelect" @sort="onSort">
                <md-table-header>
                    <md-table-row>
                        <md-table-head md-sort-by="dessert">Title</md-table-head>
                        <md-table-head md-sort-by="calories" md-numeric md-tooltip="Is this searchable by other users">Visible</md-table-head>
                    </md-table-row>
                </md-table-header>

                <md-table-body>
                    <md-table-row v-for="(row, rowIndex) in links" :key="rowIndex" :md-item="row" md-auto-select md-selection>
                        <md-table-cell v-for="(column, columnIndex) in row" :key="columnIndex" :md-numeric="columnIndex !== 'dessert' && columnIndex !== 'comment'"
                            v-if="columnIndex !== 'type'">
                            {{ column }}
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
    export default {
        name: 'Content',
        data() {
            return {  
                link: '',
                links: [
                    {
                        dessert: 'Google.com',
                        calories: 'true'
    
                    },
                    {
                        dessert: 'amazon.com',
                        calories: 'true'
                    },
                    {
                        dessert: 'reddit.com',
                        calories: 'false'
                    },
                    {
                        dessert: 'index.hu',
                        calories: 'false'
                    },
                    {
                        dessert: 'port.hu',
                        calories: 'true'
                    }
                ]
            }
        },
        methods: {
            submit: function () {
                console.log('submit' + this.link)
            },
             onSort: function () {
                console.log('onSort')
            },
             onSelect: function () {
                console.log('onSelect')
            },
             onPagination: function () {
                console.log('onPagination')
            }
        }
    }

</script>

<style scoped>
  .table{
        margin: 40px;
  }
</style>