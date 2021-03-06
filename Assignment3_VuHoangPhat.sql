USE [master]
GO
/****** Object:  Database [Assignment3_VuHoangPhat]    Script Date: 3/7/2021 11:00:58 PM ******/
CREATE DATABASE [Assignment3_VuHoangPhat]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Assignment3_VuHoangPhat', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXP\MSSQL\DATA\Assignment3_VuHoangPhat.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Assignment3_VuHoangPhat_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXP\MSSQL\DATA\Assignment3_VuHoangPhat_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Assignment3_VuHoangPhat].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET ARITHABORT OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET  MULTI_USER 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Assignment3_VuHoangPhat]
GO
/****** Object:  Table [dbo].[account]    Script Date: 3/7/2021 11:00:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[account](
	[email] [varchar](100) NOT NULL,
	[phone] [varchar](10) NULL,
	[name] [varchar](max) NULL,
	[address] [varchar](max) NULL,
	[password] [varchar](max) NULL,
	[create_date] [datetime] NULL DEFAULT (getdate()),
	[role] [bit] NULL,
	[status] [bit] NULL,
	[verify_code] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[car]    Script Date: 3/7/2021 11:00:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[car](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[car_name] [varchar](max) NULL,
	[image_car] [varchar](max) NULL,
	[color] [varchar](max) NULL,
	[year] [int] NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[create_date] [datetime] NULL DEFAULT (getdate()),
	[rate] [float] NULL,
	[id_category] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[category]    Script Date: 3/7/2021 11:00:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[category_name] [varchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[discount]    Script Date: 3/7/2021 11:00:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[discount](
	[id_discount] [int] IDENTITY(1,1) NOT NULL,
	[code_discount] [varchar](6) NULL,
	[percent_discount] [int] NULL,
	[expiration_date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_discount] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[order]    Script Date: 3/7/2021 11:00:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date_order] [date] NULL DEFAULT (getdate()),
	[email] [varchar](100) NULL,
	[id_discount] [int] NULL,
	[percent_discount] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 3/7/2021 11:00:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[order_detail](
	[rental_date] [date] NOT NULL,
	[return_date] [date] NOT NULL,
	[id_car] [int] NOT NULL,
	[id_invoice] [int] NOT NULL,
	[car_name] [varchar](max) NULL,
	[color] [varchar](max) NULL,
	[year] [int] NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[status] [bit] NULL,
	[rate] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_car] ASC,
	[id_invoice] ASC,
	[rental_date] ASC,
	[return_date] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[account] ([email], [phone], [name], [address], [password], [create_date], [role], [status], [verify_code]) VALUES (N'admin@gmail.com', N'0123456789', N'test', N'123 abc', N'123456', CAST(N'2021-03-07 14:51:24.847' AS DateTime), 1, 1, NULL)
INSERT [dbo].[account] ([email], [phone], [name], [address], [password], [create_date], [role], [status], [verify_code]) VALUES (N'phatvhse140209@fpt.edu.vn', N'0989998395', N'Vu Hoang Phat', N'123 abc', N'123456789', CAST(N'2021-03-07 15:04:18.993' AS DateTime), NULL, 1, N'kgjyuq')
INSERT [dbo].[account] ([email], [phone], [name], [address], [password], [create_date], [role], [status], [verify_code]) VALUES (N'test@gmail.com', N'0123456789', N'test', N'123 abc', N'123456', CAST(N'2021-03-07 14:51:24.843' AS DateTime), 0, 1, NULL)
SET IDENTITY_INSERT [dbo].[car] ON 

INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (1, N'Mercedes-Benz 1', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2021, 100, 10, CAST(N'2021-03-07 14:51:24.847' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (2, N'Mercedes-Benz 2', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2020, 200, 10, CAST(N'2021-03-07 14:51:24.847' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (3, N'Mercedes-Benz 3', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2021, 300, 10, CAST(N'2021-03-07 14:51:24.847' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (4, N'Mercedes-Benz 4', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2020, 350, 10, CAST(N'2021-03-07 14:51:24.847' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (5, N'Mercedes-Benz 5', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2021, 500, 10, CAST(N'2021-03-07 14:51:24.847' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (6, N'Mercedes-Benz 6', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2020, 400, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), 5.5, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (8, N'Mercedes-Benz 8', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2020, 150, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (9, N'Mercedes-Benz 9', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2021, 250, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (10, N'Mercedes-Benz 10', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2019, 600, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (11, N'Mercedes-Benz 11', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2021, 650, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (12, N'Mercedes-Benz 12', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2019, 550, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (13, N'Mercedes-Benz 13', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2018, 456, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (14, N'Mercedes-Benz 14', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2019, 789, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (15, N'Mercedes-Benz 15', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2020, 800, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (16, N'Mercedes-Benz 16', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2021, 845, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (17, N'Mercedes-Benz 17', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2020, 850, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (18, N'Mercedes-Benz 18', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2019, 123, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (19, N'Mercedes-Benz 19', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2018, 147, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (20, N'Mercedes-Benz 20', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2019, 189, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (21, N'Mercedes-Benz 21', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2019, 369, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 1)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (22, N'Ford 1', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 100, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (23, N'Ford 2', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2020, 101, 10, CAST(N'2021-03-07 14:51:24.850' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (24, N'Ford 3', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2021, 102, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (25, N'Ford 4', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 103, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (26, N'Ford 5', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2020, 104, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (27, N'Ford 6', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2021, 105, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (28, N'Ford 7', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 106, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (29, N'Ford 8', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2020, 107, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (30, N'Ford 9', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 108, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (31, N'Ford 10', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2020, 190, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (32, N'Ford 11', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2020, 109, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (33, N'Ford 12', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 110, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (34, N'Ford 13', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 180, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (35, N'Ford 14', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2020, 170, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (36, N'Ford 15', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 160, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (37, N'Ford 16', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2020, 150, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (38, N'Ford 17', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 140, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (39, N'Ford 18', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2020, 120, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (40, N'Ford 19', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 110, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (41, N'Ford 20', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 200, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (42, N'Ford 21', N'https://imgcdn.zigwheels.ph/medium/gallery/color/7/51/ford-everest-color-882014.jpg', N'gray', 2019, 500, 10, CAST(N'2021-03-07 14:51:24.853' AS DateTime), NULL, 2)
INSERT [dbo].[car] ([id], [car_name], [image_car], [color], [year], [price], [quantity], [create_date], [rate], [id_category]) VALUES (43, N'Mercedes-Benz 7', N'https://giaxenhap.com/wp-content/uploads/2019/05/Mercedes-Benz-S450L-m%C3%A0u-Lunar-Blue-Metallic.jpg', N'blue', 2021, 450, 10, CAST(N'2021-03-07 16:34:41.547' AS DateTime), NULL, 1)
SET IDENTITY_INSERT [dbo].[car] OFF
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([id], [category_name]) VALUES (1, N'Mercedes Benz')
INSERT [dbo].[category] ([id], [category_name]) VALUES (2, N'Ford')
SET IDENTITY_INSERT [dbo].[category] OFF
SET IDENTITY_INSERT [dbo].[discount] ON 

INSERT [dbo].[discount] ([id_discount], [code_discount], [percent_discount], [expiration_date]) VALUES (1, N'TfUAbb', 20, CAST(N'2022-02-03' AS Date))
INSERT [dbo].[discount] ([id_discount], [code_discount], [percent_discount], [expiration_date]) VALUES (2, N'eFWMP7', 15, CAST(N'2022-02-03' AS Date))
INSERT [dbo].[discount] ([id_discount], [code_discount], [percent_discount], [expiration_date]) VALUES (3, N'RacpjU', 30, CAST(N'2022-02-03' AS Date))
INSERT [dbo].[discount] ([id_discount], [code_discount], [percent_discount], [expiration_date]) VALUES (4, N'RMgSvH', 50, CAST(N'2022-02-03' AS Date))
INSERT [dbo].[discount] ([id_discount], [code_discount], [percent_discount], [expiration_date]) VALUES (5, N'', 0, CAST(N'2022-02-03' AS Date))
SET IDENTITY_INSERT [dbo].[discount] OFF
SET IDENTITY_INSERT [dbo].[order] ON 

INSERT [dbo].[order] ([id], [date_order], [email], [id_discount], [percent_discount]) VALUES (14, CAST(N'2021-03-07' AS Date), N'phatvhse140209@fpt.edu.vn', 1, N'20')
INSERT [dbo].[order] ([id], [date_order], [email], [id_discount], [percent_discount]) VALUES (17, CAST(N'2021-03-07' AS Date), N'phatvhse140209@fpt.edu.vn', 1, N'20')
INSERT [dbo].[order] ([id], [date_order], [email], [id_discount], [percent_discount]) VALUES (18, CAST(N'2021-03-07' AS Date), N'phatvhse140209@fpt.edu.vn', 1, N'20')
INSERT [dbo].[order] ([id], [date_order], [email], [id_discount], [percent_discount]) VALUES (21, CAST(N'2021-03-07' AS Date), N'test@gmail.com', NULL, N'20')
SET IDENTITY_INSERT [dbo].[order] OFF
INSERT [dbo].[order_detail] ([rental_date], [return_date], [id_car], [id_invoice], [car_name], [color], [year], [price], [quantity], [status], [rate]) VALUES (CAST(N'2021-03-08' AS Date), CAST(N'2021-03-09' AS Date), 6, 14, N'Mercedes-Benz 6', N'blue', 2020, 400, 1, 0, NULL)
INSERT [dbo].[order_detail] ([rental_date], [return_date], [id_car], [id_invoice], [car_name], [color], [year], [price], [quantity], [status], [rate]) VALUES (CAST(N'2021-03-07' AS Date), CAST(N'2021-03-08' AS Date), 6, 17, N'Mercedes-Benz 6', N'blue', 2020, 400, 1, 1, NULL)
INSERT [dbo].[order_detail] ([rental_date], [return_date], [id_car], [id_invoice], [car_name], [color], [year], [price], [quantity], [status], [rate]) VALUES (CAST(N'2021-03-08' AS Date), CAST(N'2021-03-09' AS Date), 6, 18, N'Mercedes-Benz 6', N'blue', 2020, 400, 1, 1, NULL)
INSERT [dbo].[order_detail] ([rental_date], [return_date], [id_car], [id_invoice], [car_name], [color], [year], [price], [quantity], [status], [rate]) VALUES (CAST(N'2021-03-08' AS Date), CAST(N'2021-03-09' AS Date), 43, 14, N'Mercedes-Benz 7', N'blue', 2021, 450, 1, 1, NULL)
INSERT [dbo].[order_detail] ([rental_date], [return_date], [id_car], [id_invoice], [car_name], [color], [year], [price], [quantity], [status], [rate]) VALUES (CAST(N'2021-03-07' AS Date), CAST(N'2021-03-08' AS Date), 43, 17, N'Mercedes-Benz 7', N'blue', 2021, 450, 1, 1, NULL)
INSERT [dbo].[order_detail] ([rental_date], [return_date], [id_car], [id_invoice], [car_name], [color], [year], [price], [quantity], [status], [rate]) VALUES (CAST(N'2021-03-08' AS Date), CAST(N'2021-03-09' AS Date), 43, 18, N'Mercedes-Benz 7', N'blue', 2021, 450, 1, 1, NULL)
ALTER TABLE [dbo].[car]  WITH CHECK ADD FOREIGN KEY([id_category])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[account] ([email])
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD FOREIGN KEY([id_discount])
REFERENCES [dbo].[discount] ([id_discount])
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD FOREIGN KEY([id_car])
REFERENCES [dbo].[car] ([id])
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD FOREIGN KEY([id_invoice])
REFERENCES [dbo].[order] ([id])
GO
USE [master]
GO
ALTER DATABASE [Assignment3_VuHoangPhat] SET  READ_WRITE 
GO
