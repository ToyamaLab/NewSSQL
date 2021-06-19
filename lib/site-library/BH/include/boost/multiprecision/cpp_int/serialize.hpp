///////////////////////////////////////////////////////////////
//  Copyright 2013 John Maddock. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
<<<<<<< HEAD
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_
=======
//  LICENSE_1_0.txt or copy at https://www.boost.org/LICENSE_1_0.txt
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#ifndef BOOST_MP_CPP_INT_SERIALIZE_HPP
#define BOOST_MP_CPP_INT_SERIALIZE_HPP

namespace boost {

<<<<<<< HEAD
namespace archive{
=======
namespace archive {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

class binary_oarchive;
class binary_iarchive;

<<<<<<< HEAD
}
=======
} // namespace archive
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace serialization {

namespace mp = boost::multiprecision;

<<<<<<< HEAD
namespace cpp_int_detail{
=======
namespace cpp_int_detail {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

using namespace boost::multiprecision;
using namespace boost::multiprecision::backends;

template <class T>
<<<<<<< HEAD
struct is_binary_archive : public mpl::false_ {};
template <>
struct is_binary_archive<boost::archive::binary_oarchive> : public mpl::true_ {};
template <>
struct is_binary_archive<boost::archive::binary_iarchive> : public mpl::true_ {};
=======
struct is_binary_archive : public mpl::false_
{};
template <>
struct is_binary_archive<boost::archive::binary_oarchive> : public mpl::true_
{};
template <>
struct is_binary_archive<boost::archive::binary_iarchive> : public mpl::true_
{};
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

//
// We have 8 serialization methods to fill out (and test), they are all permutations of:
// Load vs Store.
// Trivial or non-trivial cpp_int type.
// Binary or not archive.
//
template <class Archive, class Int>
void do_serialize(Archive& ar, Int& val, mpl::false_ const&, mpl::false_ const&, mpl::false_ const&)
{
   // Load.
   // Non-trivial.
   // Non binary.

<<<<<<< HEAD
   bool s;
   ar & s;
   std::size_t limb_count;
   std::size_t byte_count;
   ar & byte_count;
   limb_count = byte_count / sizeof(limb_type) + ((byte_count % sizeof(limb_type)) ? 1 : 0);
   val.resize(limb_count, limb_count);
   limb_type* pl = val.limbs();
   for(std::size_t i = 0; i < limb_count; ++i)
   {
      pl[i] = 0;
      for(std::size_t j = 0; (j < sizeof(limb_type)) && byte_count; ++j)
      {
         unsigned char byte;
         ar & byte;
=======
   using boost::make_nvp;
   bool        s;
   ar&         make_nvp("sign", s);
   std::size_t limb_count;
   std::size_t byte_count;
   ar&         make_nvp("byte-count", byte_count);
   limb_count = byte_count / sizeof(limb_type) + ((byte_count % sizeof(limb_type)) ? 1 : 0);
   val.resize(limb_count, limb_count);
   limb_type* pl = val.limbs();
   for (std::size_t i = 0; i < limb_count; ++i)
   {
      pl[i] = 0;
      for (std::size_t j = 0; (j < sizeof(limb_type)) && byte_count; ++j)
      {
         unsigned char byte;
         ar&           make_nvp("byte", byte);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         pl[i] |= static_cast<limb_type>(byte) << (j * CHAR_BIT);
         --byte_count;
      }
   }
<<<<<<< HEAD
   if(s != val.sign())
=======
   if (s != val.sign())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      val.negate();
   val.normalize();
}
template <class Archive, class Int>
void do_serialize(Archive& ar, Int& val, mpl::true_ const&, mpl::false_ const&, mpl::false_ const&)
{
   // Store.
   // Non-trivial.
   // Non binary.

<<<<<<< HEAD
   bool s = val.sign();
   ar & s;
   limb_type* pl = val.limbs();
   std::size_t limb_count = val.size();
   std::size_t byte_count = limb_count * sizeof(limb_type);
   ar & byte_count;

   for(std::size_t i = 0; i < limb_count; ++i)
   {
      limb_type l = pl[i];
      for(std::size_t j = 0; j < sizeof(limb_type); ++j)
      {
         unsigned char byte = static_cast<unsigned char>((l >> (j * CHAR_BIT)) & ((1u << CHAR_BIT) - 1));
         ar & byte;
=======
   using boost::make_nvp;
   bool        s = val.sign();
   ar&         make_nvp("sign", s);
   limb_type*  pl         = val.limbs();
   std::size_t limb_count = val.size();
   std::size_t byte_count = limb_count * sizeof(limb_type);
   ar&         make_nvp("byte-count", byte_count);

   for (std::size_t i = 0; i < limb_count; ++i)
   {
      limb_type l = pl[i];
      for (std::size_t j = 0; j < sizeof(limb_type); ++j)
      {
         unsigned char byte = static_cast<unsigned char>((l >> (j * CHAR_BIT)) & ((1u << CHAR_BIT) - 1));
         ar&           make_nvp("byte", byte);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      }
   }
}
template <class Archive, class Int>
void do_serialize(Archive& ar, Int& val, mpl::false_ const&, mpl::true_ const&, mpl::false_ const&)
{
   // Load.
   // Trivial.
   // Non binary.
<<<<<<< HEAD
   bool s;
   typename Int::local_limb_type l = 0;
   ar & s;
   std::size_t byte_count;
   ar & byte_count;
   for(std::size_t i = 0; i < byte_count; ++i)
   {
      unsigned char b;
      ar & b;
      l |= static_cast<typename Int::local_limb_type>(b) << (i * CHAR_BIT);
   }
   *val.limbs() = l;
   if(s != val.sign())
=======
   using boost::make_nvp;
   bool                          s;
   typename Int::local_limb_type l = 0;
   ar&                           make_nvp("sign", s);
   std::size_t                   byte_count;
   ar&                           make_nvp("byte-count", byte_count);
   for (std::size_t i = 0; i < byte_count; ++i)
   {
      unsigned char b;
      ar&           make_nvp("byte", b);
      l |= static_cast<typename Int::local_limb_type>(b) << (i * CHAR_BIT);
   }
   *val.limbs() = l;
   if (s != val.sign())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      val.negate();
}
template <class Archive, class Int>
void do_serialize(Archive& ar, Int& val, mpl::true_ const&, mpl::true_ const&, mpl::false_ const&)
{
   // Store.
   // Trivial.
   // Non binary.
<<<<<<< HEAD
   bool s = val.sign();
   typename Int::local_limb_type l = *val.limbs();
   ar & s;
   std::size_t limb_count = sizeof(l);
   ar & limb_count;
   for(std::size_t i = 0; i < limb_count; ++i)
   {
      unsigned char b = static_cast<unsigned char>(static_cast<typename Int::local_limb_type>(l >> (i * CHAR_BIT)) & static_cast<typename Int::local_limb_type>((1u << CHAR_BIT) - 1));
      ar & b;
=======
   using boost::make_nvp;
   bool                          s = val.sign();
   typename Int::local_limb_type l = *val.limbs();
   ar&                           make_nvp("sign", s);
   std::size_t                   limb_count = sizeof(l);
   ar&                           make_nvp("byte-count", limb_count);
   for (std::size_t i = 0; i < limb_count; ++i)
   {
      unsigned char b = static_cast<unsigned char>(static_cast<typename Int::local_limb_type>(l >> (i * CHAR_BIT)) & static_cast<typename Int::local_limb_type>((1u << CHAR_BIT) - 1));
      ar&           make_nvp("byte", b);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   }
}
template <class Archive, class Int>
void do_serialize(Archive& ar, Int& val, mpl::false_ const&, mpl::false_ const&, mpl::true_ const&)
{
   // Load.
   // Non-trivial.
   // Binary.
<<<<<<< HEAD
   bool s;
   std::size_t c;
   ar & s;
   ar & c;
   val.resize(c, c);
   ar.load_binary(val.limbs(), c * sizeof(limb_type));
   if(s != val.sign())
=======
   bool        s;
   std::size_t c;
   ar&         s;
   ar&         c;
   val.resize(c, c);
   ar.load_binary(val.limbs(), c * sizeof(limb_type));
   if (s != val.sign())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      val.negate();
   val.normalize();
}
template <class Archive, class Int>
void do_serialize(Archive& ar, Int& val, mpl::true_ const&, mpl::false_ const&, mpl::true_ const&)
{
   // Store.
   // Non-trivial.
   // Binary.
<<<<<<< HEAD
   bool s = val.sign();
   std::size_t c = val.size();
   ar & s;
   ar & c;
=======
   bool        s = val.sign();
   std::size_t c = val.size();
   ar&         s;
   ar&         c;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   ar.save_binary(val.limbs(), c * sizeof(limb_type));
}
template <class Archive, class Int>
void do_serialize(Archive& ar, Int& val, mpl::false_ const&, mpl::true_ const&, mpl::true_ const&)
{
   // Load.
   // Trivial.
   // Binary.
   bool s;
<<<<<<< HEAD
   ar & s;
   ar.load_binary(val.limbs(), sizeof(*val.limbs()));
   if(s != val.sign())
=======
   ar&  s;
   ar.load_binary(val.limbs(), sizeof(*val.limbs()));
   if (s != val.sign())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      val.negate();
}
template <class Archive, class Int>
void do_serialize(Archive& ar, Int& val, mpl::true_ const&, mpl::true_ const&, mpl::true_ const&)
{
   // Store.
   // Trivial.
   // Binary.
   bool s = val.sign();
<<<<<<< HEAD
   ar & s;
   ar.save_binary(val.limbs(), sizeof(*val.limbs()));
}

}

template<class Archive, unsigned MinBits, unsigned MaxBits, mp::cpp_integer_type SignType, mp::cpp_int_check_type Checked, class Allocator>
void serialize(Archive & ar, mp::cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator>& val, const unsigned int /*version*/)
{
   typedef typename Archive::is_saving save_tag;
   typedef mpl::bool_<mp::backends::is_trivial_cpp_int<mp::cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator> >::value> trivial_tag;
   typedef typename cpp_int_detail::is_binary_archive<Archive>::type binary_tag;
=======
   ar&  s;
   ar.save_binary(val.limbs(), sizeof(*val.limbs()));
}

} // namespace cpp_int_detail

template <class Archive, unsigned MinBits, unsigned MaxBits, mp::cpp_integer_type SignType, mp::cpp_int_check_type Checked, class Allocator>
void serialize(Archive& ar, mp::cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator>& val, const unsigned int /*version*/)
{
   typedef typename Archive::is_saving                                                                                               save_tag;
   typedef mpl::bool_<mp::backends::is_trivial_cpp_int<mp::cpp_int_backend<MinBits, MaxBits, SignType, Checked, Allocator> >::value> trivial_tag;
   typedef typename cpp_int_detail::is_binary_archive<Archive>::type                                                                 binary_tag;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

   // Just dispatch to the correct method:
   cpp_int_detail::do_serialize(ar, val, save_tag(), trivial_tag(), binary_tag());
}

<<<<<<< HEAD
}} // namespaces

#endif // BOOST_MP_CPP_INT_SERIALIZE_HPP

=======
} // namespace serialization
} // namespace boost

#endif // BOOST_MP_CPP_INT_SERIALIZE_HPP
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
