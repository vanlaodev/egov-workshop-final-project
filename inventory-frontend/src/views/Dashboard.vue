<template>
  <div>
    <h1>{{ $t("dashboard") }}</h1>
    <div class="row">
      <div
        v-for="chartType in chartTypes1"
        :key="chartType"
        class="col-md-6 my-2"
      >
        <b-card :header="`Dummy Chart (${chartType})`" class="shadow-sm">
          <apexchart
            :type="chartType"
            :options="chartOptions1"
            :series="series1"
          ></apexchart>
        </b-card>
      </div>

      <div class="col-md-6 my-2">
        <b-card :header="`Dummy Chart (pie)`" class="shadow-sm">
          <apexchart :options="chartOptions2" :series="series2"></apexchart>
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      chartTypes1: ["line", "area", "bar", "scatter", "heatmap", "histogram"],
      //chartTypes1: ["area"],
      chartOptions1: {
        chart: {
          id: "vuechart-example1",
        },
        xaxis: {
          categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998],
        },
      },
      series1: [
        {
          name: "chart_1",
          data: [30, 40, 35, 50, 49, 60, 70, 91],
        },
      ],
      series2: [30, 40],
    };
  },
  computed: {
    chartOptions2() {
      return {
        chart: {
          width: "100%",
          type: "pie",
        },
        labels: [this.$t("dashboard_counted"), this.$t("dashboard_notcounted")],
        theme: {
          monochrome: {
            enabled: true,
          },
        },
        plotOptions: {
          pie: {
            dataLabels: {
              offset: -5,
            },
          },
        },
        dataLabels: {
          formatter(val, opts) {
            const name = opts.w.globals.labels[opts.seriesIndex];
            return [name, val.toFixed(1) + "%"];
          },
        },
        legend: {
          show: false,
        },
      };
    },
  },
};
</script>