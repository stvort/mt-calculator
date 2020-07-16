<template>
  <v-list-item>
    <v-text-field type="number" filled dens hide-details :rules="[rules.required]" :label="equationText" v-model="equationValue">
    </v-text-field>
  </v-list-item>
</template>

<script>
export default {
  name: 'game-item',
  props: ['item', 'index'],
  
  data () {
      return {
        rules: {
          required: value => {
            return (value !== undefined)  || 'Нужно ввести хоть что-то.'
          }
        },
      }
  },
  
  computed: {
    equationText: {
      get () {
        return this.item.first_number + (this.item.type === 'm'? ' x ': ' / ') + this.item.second_number
      },

    },

    equationValue: {
      get () {
        return this.item.value || 0
      },

      set (value) {
        this.$emit('on-change', value, this.index)
      }
    }
  }
}  
</script>

<style scoped>
  .v-text-field {
    background-color: whitesmoke !important;
  }

  .v-list-item {
    margin-bottom: 10px;
  }

</style>