<template>
  <div>
    <h1>{{ $t("dashboard") }}</h1>
    <div class="row">
      <div
        v-for="(chartType, index) in chartTypes"
        :key="chartType"
        class="col-md-6 my-2"
      >
        <b-card :header="`Dummy Chart ${index + 1}`">
          <apexchart
            :type="chartType"
            :options="chartOptions"
            :series="series"
          ></apexchart>
        </b-card>
      </div>
    </div>
  </div>
</template>

<style scoped>
.box {
  box-shadow: 0px 1px 22px -12px #607d8b;
  background-color: #fff;
  padding: 25px 35px 25px 30px;
  margin: 10px 0px;
}
</style>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      chartTypes: ["line", "area", "bar", "scatter", "heatmap", "histogram"],
      chartOptions: {
        chart: {
          id: "vuechart-example",
        },
        xaxis: {
          categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998],
        },
      },
      series: [
        {
          name: "series-1",
          data: [30, 40, 35, 50, 49, 60, 70, 91],
        },
      ],
    };
  },
};
</script>