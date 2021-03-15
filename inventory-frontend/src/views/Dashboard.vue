<template>
  <div>
    <h1>{{ $t("dashboard") }}</h1>
    <div class="row">
      <!-- <div
        v-for="chartType in chartTypes1"
        :key="chartType"
        class="col-md-6 my-2"
      > -->
      <div class="col-md-6 my-2">
        <b-card :header="`Dummy Chart (bar)`" class="shadow-sm">
          <apexchart
            type="bar"
            :options="chartOptions1"
            :series="series1"
          ></apexchart>
        </b-card>
        </div>
      <div class="col-md-6 my-2">
        <b-card :header="`Dummy Chart (bar)`" class="shadow-sm">
          <apexchart
            type="bar"
            :options="chartOptions3"
            :series="series3"
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
      //chartTypes1: ["line", "area", "bar", "scatter", "heatmap", "histogram"],

      chartOptions1: {
        chart: {
          id: "vuechart-example1",
        },
        xaxis: {
          categories: [],
        },
      },
      series1: [
        {
          name: "已盤點",
          data: [],
        },
        {
          name: "未盤點",
          data: [],
        },
      ],
      series2: [],
      chartOptions3: {
        chart: {
          id: "vuechart-example3",
        },
        xaxis: {
          categories: [],
        },
      },
      series3: [
        {
          name: "盤點數目",
          data: [],
        },
      ],
    };
  },
  computed: {
    chartOptions2() {
      return {
        chart: {
          width: "100%",
          type: "pie",
        },
        //labels: [this.$t("dashboard_counted"), this.$t("dashboard_notcounted")],
        labels: [],
        theme: {
          monochrome: {
            enabled: false,
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
  methods: {
    loadDashboardData() {
      this.$api.inventoryApi
        .getDashboardProgress()
        .then((p) => this.loadDashboardProgress(p))
        .catch((e) => console.error(e));
      this.$api.inventoryApi
        .getDashboardMasterCount()
        .then((p) => this.loadDashboardMasterCount(p))
        .catch((e) => console.error(e));
      this.$api.inventoryApi
        .getDashBoardDetailCount()
        .then((p) => this.loadDashBoardDetailCount(p))
        .catch((e) => console.error(e));
    },
    loadDashboardMasterCount(data) {
      //console.log(data);
      for (let i = 0; i < data.counter.length; i++) {
        this.series2[i] = data.counter[i].count;
        this.chartOptions2.labels[i] = data.counter[i].deptName;
      }
    },
    loadDashboardProgress(data) {
      //console.log(data);
      for (let i = 0; i < data.progress.length; i++) {
        this.series1[0].data[i] = data.progress[i].finishedCount;
        this.series1[1].data[i] = data.progress[i].nonFinishCount;
        this.chartOptions1.xaxis.categories[i] = data.progress[i].masterTitle;
      }
    },
    loadDashBoardDetailCount(data) {
      //console.log(data);
      for (let i = 0; i < data.detailCount.length; i++) {
        this.series3[0].data[i] = data.detailCount[i].detailCount;
        this.chartOptions3.xaxis.categories[i] =
          data.detailCount[i].masterTitle;
      }
      //console.log( this.chartOptions3.xaxis.categories);
    },
  },
  mounted() {
    this.loadDashboardData();
  },
};
</script>