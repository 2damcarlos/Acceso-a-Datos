using MySql.Data.MySqlClient;
using System;
using System.Data;

namespace Serpis.Ad
{
    class MainClass {
        public static void Main(string[] args)
        {
			App.Instance.Connection = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas");
			App.Instance.Connection.Open();

            Categoria categoria = load(1L);
            categoria.Nombre = categoria.Nombre + "#c";
            update(categoria);

            App.Instance.Connection.Close();
        }

        private static Categoria load(object id) {
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "select * from categoria where id = @id";
			DbCommandHelper.AddParameter(dbCommand, "id", id);
			IDataReader dataReader = dbCommand.ExecuteReader();
			dataReader.Read();
			string nombre = (string)dataReader["nombre"];
			dataReader.Close();
			Categoria categoria = new Categoria();
			categoria.Id = Convert.ToInt64(id);
			categoria.Nombre = nombre;
			return categoria;
        }

        private static void update(Categoria categoria) {
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "update categoria set nombre=@nombre where id = @id";
			DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);
			DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
			dbCommand.ExecuteNonQuery();
        }
    }

	public class App {
		private IDbConnection connection;
		private App()
		{
		}

		private static App instance = new App();

		//public static App GetInstance() {
		//    return instance;
		//}
		public static App Instance
		{
			get { return instance; }
		}

		//public IDbConnection GetConnection () {
		//    return connection;
		//}
		//public void SetConnection (IDbConnection connection) {
		//    this.connection = connection;
		//}
		public IDbConnection Connection
		{
			get { return connection; }
			set { connection = value; }
		}
	}

	public class DbCommandHelper {
		public static void AddParameter(IDbCommand dbCommand, string name, object value)
		{
			IDbDataParameter dbDataParameter = dbCommand.CreateParameter();
			dbDataParameter.ParameterName = name;
			dbDataParameter.Value = value;
			dbCommand.Parameters.Add(dbDataParameter);
		}

	}
}
