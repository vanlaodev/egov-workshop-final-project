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

      <div
        v-for="chartType in chartTypes2"
        :key="chartType"
        class="col-md-6 my-2"
      >
        <b-card :header="`Dummy Chart (${chartType})`" class="shadow-sm">
          <apexchart
            :type="chartType"
            :options="chartOptions2"
            :series="series2"
          ></apexchart>
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
      //chartTypes1: ["line", "area", "bar", "scatter", "heatmap", "histogram"],
      chartTypes1: ["area"],
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
      chartTypes2: ["bar"],
      chartOptions2: {
        chart: {
          id: "vuechart-example2",
        },
        xaxis: {
          categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998],
        },
      },
    };
  },
  computed: {
    series2() {
      return [
        {
          name: this.$t("dashboard_counted"),
          data: [30, 40, 35, 50, 49, 60, 70, 91],
        },
        {
          name: this.$t("dashboard_notcounted"),
          data: [40, 50, 45, 60, 59, 70, 80, 101],
        },
      ];
    },
  },
};
</script>